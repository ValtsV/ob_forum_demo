package com.valts.ob_forum_demo;

import com.valts.ob_forum_demo.models.*;
import com.valts.ob_forum_demo.repos.*;
import com.valts.ob_forum_demo.servicios.UserService;
import com.valts.ob_forum_demo.servicios.implementations.UserServiceImpl;
import com.valts.ob_forum_demo.servicios.implementations.VotoServiceImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
		VotoRespuestaRepository votoRespuestaRepository = ctx.getBean(VotoRespuestaRepository.class);
		VotoRepository votoRepo = ctx.getBean(VotoRepository.class);



		VotoPregunta votoPregunta1 = new VotoPregunta(null, true);
		VotoPregunta votoPregunta2 = new VotoPregunta(null, true);
		VotoPregunta votoPregunta3 = new VotoPregunta(null, false);
		VotoPregunta votoPregunta4 = new VotoPregunta(null, true);
		VotoPregunta votoPregunta5 = new VotoPregunta(null, false);
		VotoPregunta votoPregunta6 = new VotoPregunta(null, true);

		VotoRespuesta votoRespuesta1 = new VotoRespuesta(null, true);
		VotoRespuesta votoRespuesta2 = new VotoRespuesta(null, false);
		VotoRespuesta votoRespuesta3 = new VotoRespuesta(null, false);
		VotoRespuesta votoRespuesta4 = new VotoRespuesta(null, true);
		VotoRespuesta votoRespuesta5 = new VotoRespuesta(null, true);
		VotoRespuesta votoRespuesta6 = new VotoRespuesta(null, true);
		VotoRespuesta votoRespuesta7 = new VotoRespuesta(null, false);
		VotoRespuesta votoRespuesta8 = new VotoRespuesta(null, true);
		VotoRespuesta votoRespuesta9 = new VotoRespuesta(null, false);
		VotoRespuesta votoRespuesta10 = new VotoRespuesta(null, true);
		VotoRespuesta votoRespuesta11 = new VotoRespuesta(null, true);
		VotoRespuesta votoRespuesta12 = new VotoRespuesta(null, false);

		Respuesta respuesta1 = new Respuesta(null, "respText1", LocalDateTime.now(), false);
		Respuesta respuesta2 = new Respuesta(null, "respText2", LocalDateTime.now(), false);
		Respuesta respuesta3 = new Respuesta(null, "respText3", LocalDateTime.now(), false);
		Respuesta respuesta4 = new Respuesta(null, "respText3", LocalDateTime.now(), false);
		Respuesta respuesta5 = new Respuesta(null, "respText3", LocalDateTime.now(), true);
		Respuesta respuesta6 = new Respuesta(null, "respText3", LocalDateTime.now(), true);
		Respuesta respuesta7 = new Respuesta(null, "respText3", LocalDateTime.now(), false);



		Pregunta pregunta1 = new Pregunta(null, "title1", "descripcion1", LocalDateTime.now(), false);
		Pregunta pregunta2 = new Pregunta(null, "title2", "descripcion2", LocalDateTime.now(), false);
		Pregunta pregunta3 = new Pregunta(null, "title3", "descripcion3", LocalDateTime.now(), true);
		Pregunta pregunta4 = new Pregunta(null, "title4", "descripcion4", LocalDateTime.now(), true);
		Pregunta pregunta5 = new Pregunta(null, "title5", "descripcion5", LocalDateTime.now(), false);


		votoPregunta1.setPregunta(pregunta1);
		votoPregunta2.setPregunta(pregunta1);
		votoPregunta3.setPregunta(pregunta1);
		votoPregunta4.setPregunta(pregunta2);
		votoPregunta5.setPregunta(pregunta1);
		votoPregunta6.setPregunta(pregunta1);

		votoRespuesta1.setRespuesta(respuesta1);
		votoRespuesta2.setRespuesta(respuesta2);
		votoRespuesta3.setRespuesta(respuesta1);
		votoRespuesta4.setRespuesta(respuesta1);
		votoRespuesta5.setRespuesta(respuesta3);
		votoRespuesta6.setRespuesta(respuesta2);
		votoRespuesta7.setRespuesta(respuesta1);
		votoRespuesta8.setRespuesta(respuesta1);
		votoRespuesta9.setRespuesta(respuesta1);
		votoRespuesta10.setRespuesta(respuesta1);
		votoRespuesta11.setRespuesta(respuesta1);
		votoRespuesta12.setRespuesta(respuesta1);

		List<Voto> wowList = new ArrayList<>();

		wowList.add(votoPregunta1);


		Curso curso1 = new Curso(null, "React", null);
		Curso curso2 = new Curso(null, "Angular", null);

		User user1 = new User(null, "username1", "password1", "mail1@mail.com", "avatar");
		User user2 = new User(null, "username2", "password2", "mail2@mail.com", "avatar");

		userRepo.save(user1);
		userRepo.save(user2);

		respuesta1.setUser(user1);
		respuesta2.setUser(user2);
		respuesta3.setUser(user1);
		respuesta4.setUser(user1);
		respuesta5.setUser(user1);
		respuesta6.setUser(user1);
		respuesta7.setUser(user1);

		respuesta1.setPregunta(pregunta1);
		respuesta2.setPregunta(pregunta1);
		respuesta3.setPregunta(pregunta2);
		respuesta4.setPregunta(pregunta1);
		respuesta5.setPregunta(pregunta1);
		respuesta6.setPregunta(pregunta1);
		respuesta7.setPregunta(pregunta1);

		pregunta1.setUser(user1);
		pregunta2.setUser(user2);
		pregunta3.setUser(user1);
		pregunta4.setUser(user2);
		pregunta5.setUser(user2);


		Tema tema1 = new Tema(null, "tema1", "descripcion o algo", true);
		Tema tema2 = new Tema(null, "tema2", "descripcion", false);
		Tema tema3 = new Tema(null, "tema3", "algo", true);
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
		pregunta2.setTema(tema2);
		pregunta3.setTema(tema1);
		pregunta4.setTema(tema2);
		pregunta5.setTema(tema2);

		temaRepo.save(tema1);
		temaRepo.save(tema2);
		temaRepo.save(tema3);
		temaRepo.save(tema4);
//		temaRepo.saveAll(temas);

//		modelRepo.save(mod1);
//		modelRepo.save(mod2);

		preguntaRepository.save(pregunta1);
		preguntaRepository.save(pregunta2);
		preguntaRepository.save(pregunta3);
		preguntaRepository.save(pregunta4);
		preguntaRepository.save(pregunta5);

		respuestaRepository.save(respuesta1);
		respuestaRepository.save(respuesta2);
		respuestaRepository.save(respuesta3);
		respuestaRepository.save(respuesta4);
		respuestaRepository.save(respuesta5);
		respuestaRepository.save(respuesta6);
		respuestaRepository.save(respuesta7);




//		VotoServiceImpl serv2 = new VotoServiceImpl(votoRepo);
//		VotoServiceImpl serv2 = new VotoServiceImpl(votoRepo, votoPreguntaRepository, votoRespuestaRepository);

//		serv2.addVoto(votoPregunta1);
//		serv2.addVoto(votoPregunta2);
//
//		serv2.addVoto(votoRespuesta1);
//		serv2.addVoto(votoRespuesta2);
//
//		serv2.updateOne(1L, votoPregunta3);
//
//		 serv2.findOne(1L);
//		List<VotoPregunta> lolz =  serv2.findAllPregunta(1L);
//		Pregunta preg = preguntaRepository.findById(1L).get();
//		System.out.println(preg);
//		System.out.println("HERE");
//		System.out.println(lolz.get(1).getPregunta());
		votoRepo.save(votoPregunta1);
		votoRepo.save(votoPregunta2);
		votoRepo.save(votoPregunta3);
		votoRepo.save(votoPregunta4);
		votoRepo.save(votoPregunta5);
		votoRepo.save(votoPregunta6);

		votoRepo.save(votoRespuesta1);
		votoRepo.save(votoRespuesta2);
		votoRepo.save(votoRespuesta3);
		votoRepo.save(votoRespuesta4);
		votoRepo.save(votoRespuesta5);
		votoRepo.save(votoRespuesta6);
		votoRepo.save(votoRespuesta7);
		votoRepo.save(votoRespuesta8);
		votoRepo.save(votoRespuesta9);
		votoRepo.save(votoRespuesta10);
		votoRepo.save(votoRespuesta11);
		votoRepo.save(votoRespuesta12);




		UserServiceImpl serv = new UserServiceImpl(userRepo, cursoRepo);

		serv.enrollInCurso(1L, 1L);


//		VotoServiceImpl servV = new VotoServiceImpl(votoRepo);
//		servV.addOne(votoPregunta1);
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
