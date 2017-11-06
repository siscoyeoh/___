package yl.enum_;

public enum TabName {
	
	SysMonitor("运行监控", 0, ""), 
	History("历史曲线", 1, ""),
	Forecast("趋势预测", 2, ""),
	ServerLog("服务器日志", 3, ""),
	Telnet("远程命令", 4, ""),
	DataTransport("数据转储", 5, ""),
	DBManage("数据库管理", 6, ""),
	Help("帮助", 99, "");
	
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