package lib;

public class TaxFunction {

	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus
	 * dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan
	 * bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan)
	 * dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena
	 * pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah
	 * sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya
	 * ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
			boolean isMarried, int numberOfChildren) {

		int tax = 0;

		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

		/* Kode Soal: */
		/*
		 * if (numberOfChildren > 3) {
		 * numberOfChildren = 3;
		 * }
		 * 
		 * if (isMarried) {
		 * tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) *
		 * numberOfMonthWorking) - deductible - (54000000 + 4500000 + (numberOfChildren
		 * * 1500000))));
		 * }else {
		 * tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) *
		 * numberOfMonthWorking) - deductible - 54000000));
		 * }
		 * 
		 * if (tax < 0) {
		 * return 0;
		 * }else {
		 * return tax;
		 * }
		 * 
		 * }
		 */

		/* Kode Perbaikan: */
		numberOfChildren = Math.min(numberOfChildren, 3);
		int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
		int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;

		tax = (int) Math.round(0.05 * (totalIncome - deductible - nonTaxableIncome));

		return (tax < 0) ? 0 : tax;
	}

	private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
		int baseNonTaxableIncome = 54000000;

		if (isMarried) {
			baseNonTaxableIncome += 4500000;
		}

		int childrenAllowance = numberOfChildren * 1500000;
		return baseNonTaxableIncome + childrenAllowance;
	}
}
