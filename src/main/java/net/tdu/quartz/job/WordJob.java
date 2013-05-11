package net.tdu.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordJob implements Job {

	private static final Logger logger = LoggerFactory
			.getLogger(WordJob.class);
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("#################");
		System.out.println(arg0.getJobDetail().getJobDataMap().get("lisi"));
	}

}
