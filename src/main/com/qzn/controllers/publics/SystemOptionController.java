package com.qzn.controllers.publics;

import java.lang.management.ManagementFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qzn.models.MonitorInfo;
import com.qzn.utils.JSONUtil;
import com.sun.management.OperatingSystemMXBean;

@Controller
public class SystemOptionController {

	@RequestMapping(value = { "monitorInfo.htm" })
	@ResponseBody
	public String monitorInfo() throws Exception {
		int kb = 1024;
		// 可使用内存
		long totalMemory = Runtime.getRuntime().totalMemory() / kb;
		// 剩余内存
		long freeMemory = Runtime.getRuntime().freeMemory() / kb;
		// 最大可使用内存
		long maxMemory = Runtime.getRuntime().maxMemory() / kb;
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		// 操作系统
		String osName = System.getProperty("os.name");
		// 总的物理内存
		long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb;
		// 剩余的物理内存
		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / kb;
		// 已使用的物理内存
		long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb;
		// 获得线程总数
		ThreadGroup parentThread;
		for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
				.getParent() != null; parentThread = parentThread.getParent())
			;
		int totalThread = parentThread.activeCount();
		// 系统cpu占用率
		double cpuRatio = osmxb.getSystemCpuLoad();
		// 系统cpu平均占用率
		double cpuRatioAverage = osmxb.getSystemLoadAverage();
		// double cpuRatio = 0;
		// if (osName.toLowerCase().startsWith("windows")) {
		// cpuRatio = SystemOptionUtil.getCpuRatioForWindows();
		// } else if (osName.toLowerCase().startsWith("linux")){
		// cpuRatio = SystemOptionUtil.getCpuRateForLinux();
		// }
		// 构造返回对象
		MonitorInfo infoBean = new MonitorInfo();
		infoBean.setFreeMemory(freeMemory);
		infoBean.setFreePhysicalMemorySize(freePhysicalMemorySize);
		infoBean.setMaxMemory(maxMemory);
		infoBean.setOsName(osName);
		infoBean.setTotalMemory(totalMemory);
		infoBean.setTotalMemorySize(totalMemorySize);
		infoBean.setTotalThread(totalThread);
		infoBean.setUsedMemory(usedMemory);
		infoBean.setCpuRatio(cpuRatio);
		infoBean.setCpuRatioAverage(cpuRatioAverage);
		return JSONUtil.convertJsonFromObject(infoBean);
	}

}
