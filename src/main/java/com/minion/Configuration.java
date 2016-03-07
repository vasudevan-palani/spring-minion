package com.minion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.minion.loader.Loader;
import com.minion.loader.excel.KronosBeanRowTransformer;
import com.minion.loader.excel.KronosLoader;
import com.minion.repo.KronosHoursRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Autowired
	private ApplicationContext cxt;

	@Bean(name = "kronosEffortLoader")
	public Loader getLKronosLoader() {
		Loader ld = new Loader();
		ld.setFileLoader(new KronosLoader(0, 19));

		KronosBeanRowTransformer tf = new KronosBeanRowTransformer();
		tf.setRepo(cxt.getBean(KronosHoursRepository.class));

		ld.setRowTransformer(tf);
		return ld;
	}

}
