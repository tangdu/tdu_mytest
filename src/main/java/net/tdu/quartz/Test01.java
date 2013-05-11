package net.tdu.quartz;

import java.util.Date;

import net.tdu.quartz.job.HelloJob;
import net.tdu.quartz.job.WordJob;

import org.junit.Test;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test01 {

	private static final Logger log = LoggerFactory.getLogger(Test01.class);

	SchedulerFactory factory=null;
	
	public Test01(){
		//调度工厂
		factory = new StdSchedulerFactory();
	}
	@Test
	public void SimpleTrigger() {
		try {
			//得到调度
			Scheduler scheduler = factory.getScheduler();

			//启动时间 
			Date runTime = DateBuilder.nextGivenSecondDate(new Date(), 10);

			//调度详情
			JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
					.withIdentity("dd", "G").build();

			//简单的调度触发器-1秒执行一次-总执行20次终止
			SimpleTrigger trigger =(SimpleTrigger) TriggerBuilder.newTrigger()
					.withIdentity("xx", "G").startAt(runTime)
					.withSchedule(
							SimpleScheduleBuilder
								.simpleSchedule()
								.withIntervalInSeconds(1)
								.withRepeatCount(20)
							).build();

			Date ft = scheduler.scheduleJob(jobDetail, trigger);
			log.info(jobDetail.getKey() + " will run at: " + ft + " and repeat: "
					+ trigger.getRepeatCount() + " times, every "
					+ trigger.getRepeatInterval() / 1000 + " seconds");

			scheduler.start();
			
			//
			SchedulerMetaData metaData = scheduler.getMetaData();
	        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
			try {
				// wait 65 seconds to show job
				Thread.sleep(65L * 1000L);
			} catch (Exception e) {
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cronSchdule(){
		try {
			Scheduler sched = factory.getScheduler();
			JobDetail jobDetail=JobBuilder.newJob(WordJob.class)
					.withIdentity("job", "GG")
					.build();
			
			jobDetail.getJobDataMap().put("tangdu", "dd");
			Trigger trigger=TriggerBuilder.newTrigger()
					.withIdentity("trigger", "GG")
					.withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * *  ?"))
					.build();
			
			
			//*************************
			JobDetail jobDetail2=JobBuilder.newJob(WordJob.class)
					.withIdentity("job233", "GG")
					.build();
			
			jobDetail2.getJobDataMap().put("lisi", "dd2");
			Trigger trigger2=TriggerBuilder.newTrigger()
					.withIdentity("trigger2", "ＧＧ")
					.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * *  ?"))
					.build();
			
			sched.scheduleJob(jobDetail, trigger);
			sched.scheduleJob(jobDetail2, trigger2);
			sched.start();
			
			try {
				Thread.sleep(60L*1000L);
			} catch (Exception e) {
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
}
