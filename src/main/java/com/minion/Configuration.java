package com.minion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.minion.loader.Loader;
import com.minion.loader.excel.KronosBeanRowTransformer;
import com.minion.loader.excel.KronosLoader;
import com.minion.repo.KronosHoursRepository;
import com.minion.repo.KronosSheetsRepository;
import com.minion.repo.UserRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Autowired
	private ApplicationContext cxt;

	@Bean(name = "kronosEffortLoader")
	public Loader getLKronosLoader() {
		Loader ld = new Loader();
		ld.setFileLoader(new KronosLoader(0, 19));

		KronosBeanRowTransformer tf = new KronosBeanRowTransformer();
		tf.setKronosHoursrepo(cxt.getBean(KronosHoursRepository.class));
		tf.setUserRepo(cxt.getBean(UserRepository.class));
		tf.setKronosSheetsRepo(cxt.getBean(KronosSheetsRepository.class));

		ld.setRowTransformer(tf);
		return ld;
	}
	
	@Bean(name = "digitizedInvoicesLoader")
	public Loader getDigitizedInvoicesLoader() {
		Loader ld = new Loader();
		ld.setFileLoader(new KronosLoader(0, 19));

		KronosBeanRowTransformer tf = new KronosBeanRowTransformer();
		tf.setKronosHoursrepo(cxt.getBean(KronosHoursRepository.class));
		tf.setUserRepo(cxt.getBean(UserRepository.class));
		tf.setKronosSheetsRepo(cxt.getBean(KronosSheetsRepository.class));

		ld.setRowTransformer(tf);
		return ld;
	}

}
