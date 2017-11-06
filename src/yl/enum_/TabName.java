package yl.enum_;

public enum TabName {
	
	SysMonitor("���м��", 0, ""), 
	History("��ʷ����", 1, ""),
	Forecast("����Ԥ��", 2, ""),
	ServerLog("��������־", 3, ""),
	Telnet("Զ������", 4, ""),
	DataTransport("����ת��", 5, ""),
	DBManage("���ݿ����", 6, ""),
	Help("����", 99, "");
	
	private String desc;
	private int index;
	private String iconPath;

	private TabName(String desc, int index, String iconPath) {
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