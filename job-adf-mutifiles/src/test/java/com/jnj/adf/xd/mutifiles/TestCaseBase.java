package com.jnj.adf.xd.mutifiles;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testJobConfig/context-config.xml", "classpath:config/adfMutifiles.xml" })
public class TestCaseBase implements InitializingBean
{
  
  @Autowired
  protected JobLauncherTestUtils launcher;
  
  public TestCaseBase()
  {
  }
  
  @Test
  public void launchJob()
  {
    try
    {
      JobExecution execution = launcher.launchJob();
      Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  @BeforeClass
  public static void beforeClass()
  {
  }
  
  @Before
  public void before() throws IOException
  {
  }
  
  @Override
  public void afterPropertiesSet() throws Exception
  {
  }
}
