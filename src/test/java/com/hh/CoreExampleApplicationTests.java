package com.hh;

import com.hh.core.file.image.util.ImagaParseUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreExampleApplicationTests {

	@Test
	public void contextLoads() {
		File file = new File("C:\\Users\\Administrator\\Desktop\\kafka-131-20190829");
		String content = ImagaParseUtil.readFileContent(file);
		String[] contentArr = content.split("\n");
		int count1 = 0;
		for (int i = 0; i < contentArr.length; i++) {
			if (!contentArr[i].contains("begin deal")
					&& !contentArr[i].contains("end deal")) {
				count1++;
			}
		}

		int count2 = 0;
		for (int i = 0; i < contentArr.length; i++) {
			if (contentArr[i].contains("begin deal")
					&& contentArr[i].contains("end deal")) {
				count2++;
			}
		}

		System.out.println(count1);
		System.out.println(count2);
		System.out.println(contentArr.length);
	}

	@Test
	public void test() {
		String str = "buffer.append(alarmMsg.getSysId()).append(SPLIT).append(alarmMsg.getSysPid()).append(SPLIT).append(alarmMsg.getMdn()).append(SPLIT).append(alarmMsg.getAlnum1()).append(SPLIT).append(alarmMsg.getAlnum2()).append(SPLIT).append(alarmMsg.getAllocate()).append(SPLIT).append(alarmMsg.getAlarmId()).append(SPLIT).append(alarmMsg.getSeverity()).append(SPLIT).append(alarmMsg.getAck()).append(SPLIT).append(alarmMsg.getOccurTime()).append(SPLIT).append(alarmMsg.getSourceType()).append(SPLIT).append(alarmMsg.getType()).append(SPLIT).append(alarmMsg.getDn()).append(SPLIT).append(alarmMsg.getOrgSeverity()).append(SPLIT).append(alarmMsg.getOrgType()).append(SPLIT).append(alarmMsg.getTitle()).append(SPLIT).append(alarmMsg.getAlarmFile()).append(SPLIT).append(alarmMsg.getOldSeverity()).append(SPLIT).append(alarmMsg.getNewFlag()).append(SPLIT).append(alarmMsg.getOpt()).append(SPLIT).append(alarmMsg.getRdefSeverity()).append(SPLIT).append(alarmMsg.getRdefType()).append(SPLIT).append(alarmMsg.getClearTime()).append(SPLIT).append(alarmMsg.getRegionName()).append(SPLIT).append(alarmMsg.getManufacturer()).append(SPLIT).append(alarmMsg.getFilterList()).append(SPLIT).append(alarmMsg.getNetType()).append(SPLIT).append(alarmMsg.getObjectClass()).append(SPLIT).append(alarmMsg.getOccurCount()).append(SPLIT).append(alarmMsg.getDevCN()).append(SPLIT).append(alarmMsg.getDevAddr()).append(SPLIT).append(alarmMsg.getDevType()).append(SPLIT).append(alarmMsg.getSvrHandle()).append(SPLIT).append(alarmMsg.getDateReception()).append(SPLIT).append(alarmMsg.getEventHandle()).append(SPLIT).append(alarmMsg.getCauseDateReception()).append(SPLIT).append(alarmMsg.getCauseEventHandle()).append(SPLIT).append(alarmMsg.getCollectBeginTime()).append(SPLIT).append(alarmMsg.getCollectEndTime()).append(SPLIT).append(alarmMsg.getCollectDuration()).append(SPLIT).append(alarmMsg.getAcceptTime()).append(SPLIT).append(alarmMsg.getTransferDuration()).append(SPLIT).append(alarmMsg.getDataDealDuration()).append(SPLIT).append(alarmMsg.getParentAlarmId()).append(SPLIT).append(\"@@@\").append(alarmMsg.getAlarmText()).append(\"$$$\").append(\"\\n\");\n";
		System.out.println(StringUtils.replace(str, ".append", "\n.append"));
	}

	@Test
	public void strSubstring() {
		String name = "";
		String result = name.substring(0, 5);
		System.out.println(result);
	}

}
