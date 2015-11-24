package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.advert.cms.business.domain.Region;
import com.advert.cms.core.service.BasicService;



@ActiveProfiles("development")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring/applicationContext-main.xml"})
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class BaseTest {
	@Autowired
	BasicService basicService;
	
	@Test
	public void testRegion(){
		List<Region> listRegion = basicService.getOneLevelRegionList();
		System.out.println(listRegion.size());
		Region region =basicService.getRegionByCode("2803070000");
		System.out.println(region.getName());
		String s = basicService.getRegionFullNameByCode("2803070000");
		System.out.println(s);
		listRegion  =basicService.getRegionListByCode("2803000000");
		System.out.println(listRegion.size());
		
	}
	
}
