package utils;
/**
 * @author h w j
 * @instruction
 * 文档上传结果
 */
public class UploadResult {
	
	/**
	 * 逻辑文件名
	 */
	private String logicFileName;
	
	/**
	 * 源文件名
	 */
	private String sourceFileName;
	
	/**
	 * 上传结果
	 */
	private boolean result = false;
	
	/**
	 * 上传结果消息
	 */
	private String msg;
	
	/**
	 * 是否因大小，类型不合法而拒绝
	 */
	private boolean isRefuse = false;
	
	
	
	/**
	 * 文件的大小
	 */
	private long size;
	
	
	


	public boolean isRefuse() {
		return isRefuse;
	}

	public boolean getIsRefuse() {
		return isRefuse;
	}

	public void setRefuse(boolean isRefuse) {
		this.isRefuse = isRefuse;
	}


	public String getLogicFileName() {
		return logicFileName;
	}


	public void setLogicFileName(String logicFileName) {
		this.logicFileName = logicFileName;
	}


	public String getSourceFileName() {
		return sourceFileName;
	}


	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}


	public boolean isSuccess() {
		return result;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public long getSize() {
		return size;
	}


	public void setSize(long size) {
		this.size = size;
	}
	
}
