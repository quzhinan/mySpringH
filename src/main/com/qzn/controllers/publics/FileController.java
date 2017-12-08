package com.qzn.controllers.publics;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qzn.utils.FilesIOUtil;
import com.qzn.utils.PropertyUtil;

@Controller
public class FileController {
	
	@RequestMapping(value = { "fileOperation.htm" })
	public String fileOperation() {
		return "file";
	}

	@RequestMapping(value = { "singleFileUpload.htm" })
	public String singleFileUpload(MultipartFile singleFileUpload, Model model) throws Exception {
		String filePath = PropertyUtil.getPropertyValue("file.savePath");
		try {
			FilesIOUtil.uploadSingleFile(singleFileUpload, filePath);
			model.addAttribute("singleResult", "上传成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("singleResult", e.getMessage());
		}

		return "file";
	}

	@RequestMapping(value = { "multipleFileUpload.htm" })
	public String multipleFileUpload(@RequestParam(value = "multipleFileUpload") List<MultipartFile> multipleFileUpload,
			Model model) throws Exception {
		String filePath = PropertyUtil.getPropertyValue("file.savePath");
		try {
			FilesIOUtil.uploadMultiFiles(multipleFileUpload, filePath);
			model.addAttribute("multipleResult", "上传成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("multipleResult", e.getMessage());
		}

		return "file";
	}

}
