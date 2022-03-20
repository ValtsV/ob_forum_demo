package com.valts.ob_forum_demo;

import com.valts.ob_forum_demo.models.Curso;
import com.valts.ob_forum_demo.models.Modulo;
import com.valts.ob_forum_demo.models.Tema;
import com.valts.ob_forum_demo.models.User;
import com.valts.ob_forum_demo.repos.CursoRepository;
import com.valts.ob_forum_demo.repos.ModuloRepository;
import com.valts.ob_forum_demo.repos.TemaRepository;
import com.valts.ob_forum_demo.repos.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ObForumDemoApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(ObForumDemoApplication.class, args);

		UserRepository userRepo = ctx.getBean(UserRepository.class);
		CursoRepository cursoRepo = ctx.getBean(CursoRepository.class);
		ModuloRepository modelRepo = ctx.getBean(ModuloRepository.class);
		TemaRepository temaRepo = ctx.getBean((TemaRepository.class));

		Curso curso1 = new Curso(null, "React", null);
		Curso curso2 = new Curso(null, "Angular", null);



		Tema tema1 = new Tema(null, "tema1", "descripcion o algo", false);
		Tema tema2 = new Tema(null, "tema2", "descripcion", false);
		Tema tema3 = new Tema(null, "tema3", "algo", false);



		tema1.setCurso(curso1);

		tema2.setCurso(curso1);
		tema3.setCurso(curso2);

		Modulo mod1 = new Modulo(null, "modulo title", "modulo desc");
		Modulo mod2 = new Modulo(null, "modulo title2", "modulo desc2");

		mod1.setTema(tema2);
		mod2.setTema(tema3);

		cursoRepo.save(curso1);
		cursoRepo.save(curso2);

		List<Tema> temas = new ArrayList<>();
		temas.add(tema1);
		temas.add(tema2);
		temas.add(tema3);

		modelRepo.save(mod1);
		modelRepo.save(mod2);


		temaRepo.saveAll(temas);

	}

}
