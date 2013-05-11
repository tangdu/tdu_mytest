package net.code.bean;

/**
 * 表单或是表格bean
 * 
 * @author tangdu
 * 
 * @time 2013-4-21 上午11:04:34
 */
public class FormBean {

	private String header;
	private String dataIndex;
	private String type;
	private boolean allowBlank;
	private boolean fullRow;// 填充行

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAllowBlank() {
		return allowBlank;
	}

	public void setAllowBlank(boolean allowBlank) {
		this.allowBlank = allowBlank;
	}

	public boolean isFullRow() {
		return fullRow;
	}

	public void setFullRow(boolean fullRow) {
		this.fullRow = fullRow;
	}

	public FormBean(String header, String dataIndex) {
		super();
		this.header = header;
		this.dataIndex = dataIndex;
	}

	public FormBean(String header, String dataIndex, String type,
			boolean allowBlank) {
		super();
		this.header = header;
		this.dataIndex = dataIndex;
		this.type = type;
		this.allowBlank = allowBlank;
	}

	public FormBean(String header, String dataIndex, String type,
			boolean allowBlank, boolean fullRow) {
		super();
		this.header = header;
		this.dataIndex = dataIndex;
		this.type = type;
		this.allowBlank = allowBlank;
		this.fullRow = fullRow;
	}

}
