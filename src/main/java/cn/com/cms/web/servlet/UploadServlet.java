package cn.com.cms.web.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import cn.com.cms.common.ImageUtils;
import cn.com.cms.common.PathCommonConstant;
import cn.com.cms.common.Resources;
import cn.com.cms.common.Validator;
import cn.com.cms.common.WebUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class upload
 */
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1429479051595590315L;

	private static final transient Logger log = Logger
			.getLogger(UploadServlet.class);
	// n0图象宽度
	private static final int IMAGE_N0_WIDTH = Integer.parseInt(Resources.getData("IMAGE_N0_WIDTH").trim());
	// n0图象高度
	private static final int IMAGE_N0_HEIGHT = Integer.parseInt(Resources.getData("IMAGE_N0_WIDTH").trim());
	// n1图象宽度
	private static final int IMAGE_N1_WIDTH = Integer.parseInt(Resources.getData("IMAGE_N1_WIDTH").trim());
	// n1图象高度
	private static final int IMAGE_N1_HEIGHT = Integer.parseInt(Resources.getData("IMAGE_N1_WIDTH").trim());
	// n2图象宽度
	private static final int IMAGE_N2_WIDTH = Integer.parseInt(Resources.getData("IMAGE_N2_WIDTH").trim());
	// n2图象高度
	private static final int IMAGE_N2_HEIGHT = Integer.parseInt(Resources.getData("IMAGE_N2_WIDTH").trim());
	// n3图像宽度
	private static final int IMAGE_N3_WIDTH = Integer.parseInt(Resources.getData("IMAGE_N3_WIDTH").trim());
	// n3图像高度
	private static final int IMAGE_N3_HEIGHT = Integer.parseInt(Resources.getData("IMAGE_N3_WIDTH").trim());
	//
	SimpleDateFormat sdf = new SimpleDateFormat("/yyyyMM/");
	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("UploadAction init");

		response.reset();
		response.setContentType("text/plain;charset="
				+ PathCommonConstant.DEFAULT_ENCODE);

		// 上传文件类型（图片image，动画flash，多媒体media，文件file）
		String dirName = request.getParameter("dir");
		if (Validator.isNullEmpty(dirName)) {
			dirName = "file";
		}
		// 是否选择了文件
		if (!ServletFileUpload.isMultipartContent(request)) {
			response.getWriter().write(getError("请选择文件"));
			return;
		}
		// 路径变量
		String savePath = ""; // 文件保存路径
		String saveUrl = ""; // 文件保存目录URL(返前台)
		// n0到n4保存路径
		String savePathN0 = "";
		String savePathN1 = "";
		String savePathN2 = "";
		String savePathN3 = "";
		String savePathN4 = "";
		//子文件夹
		String forDate=sdf.format(new Date());
		
		// 根据是否是图片处理保存路径
		if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals(dirName)) {
			// 文件保存目录路径
			savePathN0 = Resources.getData("UPLOAD_ROOT_FOLDER") + dirName
					+ PathCommonConstant.PATH_SEPARATOR + "n0"
					+forDate;
			savePathN1 = Resources.getData("UPLOAD_ROOT_FOLDER") + dirName
					+ PathCommonConstant.PATH_SEPARATOR + "n1"
					+forDate;
			savePathN2 = Resources.getData("UPLOAD_ROOT_FOLDER") + dirName
					+ PathCommonConstant.PATH_SEPARATOR + "n2"
					+forDate;
			savePathN3 = Resources.getData("UPLOAD_ROOT_FOLDER") + dirName
					+ PathCommonConstant.PATH_SEPARATOR + "n3"
					+forDate;
			savePathN4 = Resources.getData("UPLOAD_ROOT_FOLDER") + dirName
					+ PathCommonConstant.PATH_SEPARATOR + "n4"
					+forDate;
			// 创建文件夹
			File saveDirFileN0 = new File(savePathN0);
			if (!saveDirFileN0.exists()) {
				saveDirFileN0.mkdirs();
			}
			File saveDirFileN1 = new File(savePathN1);
			if (!saveDirFileN1.exists()) {
				saveDirFileN1.mkdirs();
			}
			File saveDirFileN2 = new File(savePathN2);
			if (!saveDirFileN2.exists()) {
				saveDirFileN2.mkdirs();
			}
			File saveDirFileN3 = new File(savePathN3);
			if (!saveDirFileN3.exists()) {
				saveDirFileN3.mkdirs();
			}
			// 检查写权限
			if (!saveDirFileN0.canWrite()) {
				response.getWriter().write(getError("上传目录没有写权限"));
				return;
			}
			if (!saveDirFileN1.canWrite()) {
				response.getWriter().write(getError("上传目录没有写权限"));
				return;
			}
			if (!saveDirFileN2.canWrite()) {
				response.getWriter().write(getError("上传目录没有写权限"));
				return;
			}
			if (!saveDirFileN3.canWrite()) {
				response.getWriter().write(getError("上传目录没有写权限"));
				return;
			}
			// 原图将在n4保存
			savePath = savePathN4;
			// 文件保存目录URL
			String isRichEditor = request.getParameter(PathCommonConstant.IS_RICH_EDITOR);
			if (!Validator.isNullEmpty(isRichEditor)
					&& isRichEditor.equals("1")) {
				// 在富文本编辑器内上传的返n3
				saveUrl =  Resources.getData("UPLOAD_ROOT_FOLDER_URL") + "/"
						+ dirName + "/" + "n3" +forDate+ "/";
			} else {
				// 在其他地方上传的返n1
				saveUrl =  Resources.getData("UPLOAD_ROOT_FOLDER_URL") + "/"
						+ dirName + "/" + "n1" +forDate + "/";
			}
		} else {
			// 非图片的保存路径中没有Nx
			// 文件保存目录路径
			savePath = Resources.getData("UPLOAD_ROOT_FOLDER") + dirName
					+forDate;

			// 文件保存目录URL
			saveUrl = Resources.getData("UPLOAD_ROOT_FOLDER_URL") + "/" + dirName
					 +forDate+ "/";
		}

		// 创建文件夹
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		// 检查目录写权限
		if (!saveDirFile.canWrite()) {
			response.getWriter().write(getError("上传目录没有写权限"));
			return;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
//			String fileName = item.getName();
//			long fileSize = item.getSize();
			// 普通文件表单，没有FORM
			if (!item.isFormField()) {

				// 上传文件的名称
				String name = item.getName();
				if (Validator.isNullEmpty(name)) {
					response.getWriter().write(getError("上传文件没有文件名"));
					return;
				}

				// 检查文件大小
				if (!checkFileSize(item.getSize(), dirName)) {
					response.getWriter().write(getError("上传文件大小超过限制"));
					return;
				}

				// 检查扩展名
				String fileExt = WebUtils.getFileExt(name);
				if (!checkExt(dirName, fileExt)) {
					response.getWriter().write(getError("上传文件扩展名不正确"));
					return;
				}

				// 创建新的文件名
				String newFileName = WebUtils.getTime("yyyyMMddHHmmss")
						+ WebUtils.getRandomString(5) + "." + fileExt;

				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);

					// // 图加水印
					// if ("image".equals(dirName) && "product".equals(key)
					// && "show".equals(picType)) {
					// ImageUtils.watermark(savePath, newFileName);
					// }

					// // 生成缩略图
					// createThumbnails(dirName, key, savePath, newFileName,
					// picType);

					// 对于图片,存储n0,n1,n2,n3
					if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE
							.equals(dirName)) {
						ImageUtils.resizeNx(savePath, savePathN0, newFileName,
								newFileName, IMAGE_N0_WIDTH, IMAGE_N0_HEIGHT,
								true);
						ImageUtils.resizeNx(savePath, savePathN1, newFileName,
								newFileName, IMAGE_N1_WIDTH, IMAGE_N1_HEIGHT,
								false);
						ImageUtils.resizeNx(savePath, savePathN2, newFileName,
								newFileName, IMAGE_N2_WIDTH, IMAGE_N2_HEIGHT,
								false);
						ImageUtils.resizeNx(savePath, savePathN3, newFileName,
								newFileName, IMAGE_N3_WIDTH, IMAGE_N3_HEIGHT,
								false);
					}

				} catch (Exception e) {
					response.getWriter().write(getError("上传文件失败"));
					log.error(PathCommonConstant.LOG_ERROR_TITLE, e);
					return;
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", (saveUrl + newFileName).replaceAll("//","/").replaceAll("//","/"));
				obj.put("fileName", name);
				response.getWriter().write(obj.toJSONString());
			}
		}
	}

	/**
	 * 检查文件大小
	 * 
	 * @param size
	 *            上传文件的大小
	 * @param type
	 *            上传文件的类型
	 * @return
	 */
	private boolean checkFileSize(long size, String type) {
		boolean flag = true;
		if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals(type)) {
			if (size > PathCommonConstant.UPLOAD_PIC_MAX_SIZE) {
				flag = false;
			}
		} else if (PathCommonConstant.UPLOAD_CATAGORY_FLASH.equals(type)
				|| PathCommonConstant.UPLOAD_CATAGORY_MEDIA.equals(type)) {
			if (size > PathCommonConstant.UPLOAD_VIDEO_MAX_SIZE) {
				flag = false;
			}
		}
		return flag;
	}
	/**
	 * <p>
	 * 返回JSON消息
	 * </p>
	 * 
	 * @return JSON字符串
	 */
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

	/**
	 * <p>
	 * 检查文件扩展名是否合法
	 * </p>
	 * 
	 * @param type
	 *            上传类型
	 * @param ext
	 *            文件扩展名
	 * @return true:合法 false:不合法
	 */
	private boolean checkExt(String type, String ext) {

		// 图片
		if (PathCommonConstant.UPLOAD_CATAGORY_IMAGE.equals(type)) {
			return PathCommonConstant.UPLOAD_TYPE_IMAGE.contains(ext);
			// 动画或者多媒体
		} else if (PathCommonConstant.UPLOAD_CATAGORY_FLASH.equals(type)
				|| PathCommonConstant.UPLOAD_CATAGORY_MEDIA.equals(type)) {
			return PathCommonConstant.UPLOAD_TYPE_MEDIA.contains(ext);
			// 普通文件
		} else if (PathCommonConstant.UPLOAD_CATAGORY_FILE.equals(type)) {
			return true;
		}
		return false;
	}
}
