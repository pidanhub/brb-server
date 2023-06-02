package com.save.brbserver.controller;

import com.save.brbserver.config.ConstantFields;
import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.service.MomentsService;
import com.save.brbserver.service.UserService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author:Zzs
 * @Description: Store jpg/png/text file
 * @DateTime: 2023/5/1 18:30
 **/

@RestController
@SuppressWarnings ("all")
@RequestMapping ("/file")
public class FileController {
	
	private static final String SUFFIX = "-thumbnail";
	@Autowired
	private UserService userService;
	@Autowired
	private MomentsService momentsService;
	
	@PostMapping ("/upload-head")
	public ResponseEntity<?> postUserHeadSculpture (@RequestParam ("username") String username, @RequestParam ("file") MultipartFile file) {
		try {
			InputStream in = file.getInputStream();
			String timeStamp = ConstantFields.dateToString(new Date());
			String[] tss = timeStamp.split("[:\\s-]");
			String fileNameHeadToUniqueOneUser = "/" + userService.getUserIdByName(username) + "_";
			String originalFileName = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1]; // 后缀名
			StringBuilder newFileName = new StringBuilder(fileNameHeadToUniqueOneUser);
			for (String s : tss)
				newFileName.append(s);
			newFileName.append("_").append(UUID.randomUUID().toString().substring(0, 10));
			String s = ConstantFields.HEAD_PATH + "/" + tss[0] + tss[1] + tss[2];
			File dir = new File(ConstantFields.getImagePath() + s);
			if (!dir.exists())
				dir.mkdirs();
			File targetFile = new File(ConstantFields.getImagePath() + s, newFileName.toString() + "." + originalFileName);
			FileOutputStream out = new FileOutputStream(targetFile);
			IOUtils.copy(in, out);
			out.close();
			in.close();
			String thumbnailDir = ConstantFields.HEAD_PATH + SUFFIX + "/"
					+ tss[0] + tss[1] + tss[2];
			dir = new File(ConstantFields.getImagePath() + thumbnailDir);
			if (!dir.exists())
				dir.mkdirs();
			String thumbnailPath = thumbnailDir + newFileName.toString() + SUFFIX + "." + originalFileName;
			Thumbnails.of(targetFile).scale(0.8f).outputQuality(0.5f)
					.toOutputStream(new FileOutputStream(ConstantFields.getImagePath() + thumbnailPath));
			userService.postUserHeadSculpture(username, thumbnailPath);
			return new ResponseEntity<>(ResponseEntity.SUCCESS, null, "上传成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "未知错误");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "上传失败，请再试一次");
		}
	}
	
	@PostMapping ("/upload-moment-images")
	public ResponseEntity<?> postMomentImages (@RequestParam ("username") String username, @RequestParam ("momentsId") Long id1, @RequestParam ("picId") Integer id2,
	                                           @RequestParam ("file") MultipartFile file) {
		try {
			InputStream in = file.getInputStream();
			
			String timeStamp = ConstantFields.dateToString(new Date());
			String[] tss = timeStamp.split("[:\\s-]");
			String messageOnPictures = "/" + id1 + "_" + id2 + "_";
			String originalFileName = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
			StringBuilder newFileName = new StringBuilder(messageOnPictures);
			for (String s : tss)
				newFileName.append(s);
			newFileName.append("_").append(UUID.randomUUID().toString().substring(0, 10));
			String s = ConstantFields.MOMENTS_PATH + "/" + tss[0] + tss[1] + tss[2];
			File dir = new File(ConstantFields.getImagePath() + s);
			if (!dir.exists())
				dir.mkdirs();
			
			File targetFile = new File(ConstantFields.getImagePath() + s,
					newFileName.toString() + "." + originalFileName);
			FileOutputStream out = new FileOutputStream(targetFile);
			IOUtils.copy(in, out);
			out.close();
			in.close();
			
			String thumbnailDir = ConstantFields.MOMENTS_PATH + SUFFIX + "/"
					+ tss[0] + tss[1] + tss[2];
			dir = new File(ConstantFields.getImagePath() + thumbnailDir);
			if (!dir.exists())
				dir.mkdirs();
			String thumbnailPath = thumbnailDir + newFileName.toString() + SUFFIX + "." + originalFileName;
			Thumbnails.of(targetFile).scale(0.8f).outputQuality(0.5f)
					.toOutputStream(new FileOutputStream(ConstantFields.getImagePath() + thumbnailPath));
			boolean success = momentsService.setImages(id1, id2, thumbnailPath)
					&& momentsService.setOriginImages(id1, id2, s + newFileName + "." + originalFileName);
			if (success)
				return new ResponseEntity<>(ResponseEntity.SUCCESS, null, "上传成功");
			else
				return new ResponseEntity<>(ResponseEntity.FAILED, null, "上传失败");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "未知错误");
		} catch (Exception e) {
			e.printStackTrace();
			Throwable cause = e.getCause();
			if (cause instanceof SQLIntegrityConstraintViolationException)
				return new ResponseEntity<>(ResponseEntity.WRONG_NUMBERINGS, null, "图片重复编号");
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "上传失败");
		}
	}
	
}
