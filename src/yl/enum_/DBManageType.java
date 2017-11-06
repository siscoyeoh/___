package yl.enum_;

public enum DBManageType {
	
	Tablespace("表空间", 0, ""), 
	Session("会话", 1, "");
	
	private String desc;
	private int index;
	private String iconPath;

	private DBManageType(String desc, int index, String iconPath) {
		this.desc = desc;
		this.index = index;
		this.iconPath = iconPath;
	}

	public String getDescription() {
		return desc;
	}

	public int getTabIndex() {
		return index;
	}

	public String getIconPath() {
		return iconPath;
	}

	@Override
	public String toString() {
		return name();
	};
	
}
