package com.valts.ob_forum_demo;

import com.valts.ob_forum_demo.models.*;
import com.valts.ob_forum_demo.repos.*;
import com.valts.ob_forum_demo.servicios.UserService;
import com.valts.ob_forum_demo.servicios.implementations.UserServiceImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ObForumDemoApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(ObForumDemoApplication.class, args);

		UserRepository userRepo = ctx.getBean(UserRepository.class);
		CursoRepository cursoRepo = ctx.getBean(CursoRepository.class);
		ModuloRepository modelRepo = ctx.getBean(ModuloRepository.class);
		TemaRepository temaRepo = ctx.getBean((TemaRepository.class));
		PreguntaRepository preguntaRepository = ctx.getBean(PreguntaRepository.class);
		RespuestaRepository respuestaRepository = ctx.getBean(RespuestaRepository.class);
		VotoPreguntaRepository votoPreguntaRepository = ctx.getBean(VotoPreguntaRepository.class);

		VotoPregunta votoPregunta1 = new VotoPregunta(null, true);
		VotoPregunta votoPregunta2 = new VotoPregunta(null, true);
		VotoPregunta votoPregunta3 = new VotoPregunta(null, false);
		VotoPregunta votoPregunta4 = new VotoPregunta(null, true);
		VotoPregunta votoPregunta5 = new VotoPregunta(null, false);
		VotoPregunta votoPregunta6 = new VotoPregunta(null, true);

		Respuesta respuesta1 = new Respuesta(null, "respText1", LocalDate.now(), false);
		Respuesta respuesta2 = new Respuesta(null, "respText2", LocalDate.now(), false);
		Respuesta respuesta3 = new Respuesta(null, "respText3", LocalDate.now(), false);



		Pregunta pregunta1 = new Pregunta(null, "title1", "descripcion1", LocalDate.now(), false);
		Pregunta pregunta2 = new Pregunta(null, "title2", "descripcion2", LocalDate.now(), false);


		votoPregunta1.setPregunta(pregunta1);
		votoPregunta2.setPregunta(pregunta1);
		votoPregunta3.setPregunta(pregunta1);
		votoPregunta4.setPregunta(pregunta1);
		votoPregunta5.setPregunta(pregunta1);
		votoPregunta6.setPregunta(pregunta1);


		Curso curso1 = new Curso(null, "React", null);
		Curso curso2 = new Curso(null, "Angular", null);

		User user1 = new User(null, "username1", "password1", "mail1@mail.com", "avatar",null);
		User user2 = new User(null, "username2", "password2", "mail2@mail.com", "avatar",null);

		userRepo.save(user1);
		userRepo.save(user2);

		respuesta1.setUser(user1);
		respuesta2.setUser(user2);
		respuesta3.setUser(user1);

		respuesta1.setPregunta(pregunta1);
		respuesta2.setPregunta(pregunta1);
		respuesta3.setPregunta(pregunta2);

		pregunta1.setUser(user1);
		pregunta2.setUser(user2);


		Tema tema1 = new Tema(null, "tema1", "descripcion o algo", false);
		Tema tema2 = new Tema(null, "tema2", "descripcion", false);
		Tema tema3 = new Tema(null, "tema3", "algo", false);
		Tema tema4 = new Tema(null, "tema4", "algo", false);





		Modulo mod1 = new Modulo(null, "modulo title", "modulo desc");
		Modulo mod2 = new Modulo(null, "modulo title2", "modulo desc2");

//		tema2.setModulo(mod1);
//		mod1.getTemas().add(tema1);
//		mod1.getTemas().add(tema2);
//		mod2.getTemas().add(tema3);

		modelRepo.save(mod1);
		modelRepo.save(mod2);

		tema1.setModulo(mod1);
		tema2.setModulo(mod2);
		tema3.setModulo(mod2);




		tema1.setCurso(curso1);

		tema2.setCurso(curso1);
		tema3.setCurso(curso2);

		cursoRepo.save(curso1);
		cursoRepo.save(curso2);

		List<Tema> temas = new ArrayList<>();
		temas.add(tema1);
		temas.add(tema2);
		temas.add(tema3);


		pregunta1.setTema(tema1);
		pregunta2.setTema(tema1);

		temaRepo.save(tema1);
		temaRepo.save(tema2);
		temaRepo.save(tema3);
		temaRepo.save(tema4);
//		temaRepo.saveAll(temas);

//		modelRepo.save(mod1);
//		modelRepo.save(mod2);

		preguntaRepository.save(pregunta1);
		preguntaRepository.save(pregunta2);

		respuestaRepository.save(respuesta1);
		respuestaRepository.save(respuesta2);
		respuestaRepository.save(respuesta3);

		votoPreguntaRepository.save(votoPregunta1);
		votoPreguntaRepository.save(votoPregunta2);
		votoPreguntaRepository.save(votoPregunta3);
		votoPreguntaRepository.save(votoPregunta4);
		votoPreguntaRepository.save(votoPregunta5);
		votoPreguntaRepository.save(votoPregunta6);

		UserServiceImpl serv = new UserServiceImpl(userRepo, cursoRepo);

		serv.enrollInCurso(1L, 1L);
//		Curso curso = cursoRepo.getById(1L);
//
//		System.out.println(curso);
//		User user = userRepo.getById(1L);
//		System.out.println(user);
//		user.getAttendedCursos().add(curso);
//		curso.getEnrolledUsers().add(user);
//		curso.getEnrolledUsers();
//		cursoRepo.save(curso);
//		Pregunta pregunta = new Pregunta(null, "pregunta title", "pregunta description", LocalDate.now(), )
	}



}
