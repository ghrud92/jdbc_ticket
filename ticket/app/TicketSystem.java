package com.estsoft.ticket.app;

import java.util.List;
import java.util.Scanner;

import com.estsoft.ticket.dao.ReserveDAO;
import com.estsoft.ticket.vo.ReserveVO;

public class TicketSystem {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice=0;
		while(true){
			System.out.println("== Ticketing System ==");
			System.out.println("1. Reserve");
			System.out.println("2. Cancel");
			System.out.println("3. Lookup");
			System.out.println("4. Exit");
			System.out.print("> ");
			choice = scanner.nextInt();	//입력 받음
			if(choice == 4)
				break;
			if(choice == 1)
				Reserve();
			else if(choice == 2)
				Cancel();
			else if(choice == 3)
				Lookup();
			else
				System.out.println("The number is wrong. Please try again.");
		}
		scanner.close();
	}
	
	public static void Reserve(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("== Reservation==\n1. The Lord of the Rings\n2. The Matrix\n3. Pride & Prejudice");
		System.out.print("> ");
		int movie = scanner.nextInt();
		System.out.println("Phone:");
		String phone = scanner.next();
		System.out.println("How many tickets?");
		int count = scanner.nextInt();
		
		ReserveVO reserveVo = new ReserveVO();
		reserveVo.setMovie_no((long)movie);
		reserveVo.setPhone(phone);
		reserveVo.setCount(count);
		
		if(new ReserveDAO().insertCheck(reserveVo)){
			System.out.println("You already reserved this movie.");
			return;
		}
		
		new ReserveDAO().insert(reserveVo);
		System.out.println("Reserved.");
	}
	
	public static void Cancel(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Phone:");
		String phone = scanner.nextLine();
		
		boolean check = show(phone);
		if(!check){
			scanner.close();
			return;
		}
		System.out.print("> ");
		
		int movie_no = scanner.nextInt();
		
		ReserveVO reserveVo = new ReserveVO();
		reserveVo.setPhone(phone);
		reserveVo.setMovie_no((long)movie_no);
		
		check = new ReserveDAO().check(reserveVo);
		
		if(!check){
			scanner.close();
			return;
		}
		new ReserveDAO().delete(reserveVo);
		System.out.println("Canceled.");
	}
	
	public static void Lookup(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Phone:");
		String phone = scanner.nextLine();
		show(phone);
	}
	
	public static boolean show(String phone){
		ReserveVO reserveVo = new ReserveVO();
		reserveVo.setPhone(phone);
		List<ReserveVO> list = new ReserveDAO().getList(reserveVo);
		if(list == null){
			System.out.println("Nothing reserved.");
			return false;
		}
		System.out.println("== Lookup ==");
		int i = 1;
		for(ReserveVO r : list){
			System.out.println(i+"."+r);
			i++;
		}
		return true;
	}
}
