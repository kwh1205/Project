package com.oracle.tour.service;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.tour.dao.WHDao;
import com.oracle.tour.dto.Member;

@Service
@Transactional
public class MailSendService2 {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private WHDao whDao;
	
	
	public String mailSend(Member member) {
	System.out.println("MailSendService mailSend start..");
	System.out.println("mailSending...");
	String tomail = "qhtmej1@nate.com";
	String setfrom = "tidnj0505@gmail.com";
	String title = "mailTransport 입니다";
	String tempPassword="";
	try {
		//Mime 전자우편 Internet 표준 Format - 프로토콜
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
		messageHelper.setFrom(setfrom); //보내는사람 생략하거나 하면 정상작동x//yml. 프로퍼티스 에서 설정한대로들어감 보내는사람
		messageHelper.setTo(tomail);    //받는사람 e메일
		messageHelper.setSubject(title);//메일제목 생략가능
		tempPassword = (int)(Math.random()*999999)+1+"";
		messageHelper.setText("임시 비밀번호입니다 :"+ tempPassword);   //settext - 메일내용,
		System.out.println("임시 비밀번호입니다: "+tempPassword);
		DataSource dataSource = new FileDataSource("c:\\log\\jung1.jpg");
		messageHelper.addAttachment(MimeUtility.encodeText("airport.png","UTF-8","B"), dataSource);
		mailSender.send(message);
		//s.tempPw(u_id,tempPassword) ; //db에 비밀번호를 임시비밀번호로 업데이트
	}catch (Exception e) {
		tempPassword="error";	
	}
	return "tempPassword";
}
	
}
