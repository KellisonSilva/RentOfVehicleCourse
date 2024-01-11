package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class ProgramMain {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		System.out.println("+++++++++++++++++Entre com os dados do aluguel+++++++++++++++++");
		System.out.print("Modelo do carro: ");
		String carmodel = sc.nextLine();
		System.out.println("Retirada (dd/MM/yyyy hh:mm:) ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.println("Retorno (dd/MM/yyyy hh:mm:) ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

		CarRental cr = new CarRental(start, finish, new Vehicle(carmodel));

		System.out.println("Entre com pre�o por hora:");
		double pricePerHour = sc.nextDouble();
		System.out.println("Entre com o pre�o por dia:");
		double pricePerDay = sc.nextDouble();

		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rentalService.processinVoice(cr);

		System.out.println("          Fatura          ");
		System.out.print("Pagamento b�sico: " + String.format("%.2f", cr.getInvoice().getBasicPayment())  + "\n");
		System.out.print("Imposto: " + String.format("%.2f", cr.getInvoice().getTax())  + "\n");
		System.out.print("Pagamento total: " + String.format("%.2f", cr.getInvoice().totalPayment()));

		sc.close();

	}

}
