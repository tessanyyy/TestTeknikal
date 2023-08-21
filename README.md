# TestTeknikal

Penjelasan script :

1. Memulai WebDriver dan membuka situs web pemesanan. Menggunakan ChromeDriver untuk mengontrol browser Chrome.
   "@Given("^I have opened the booking website$")"
   
2. Mempersiapkan data pemesanan dan jadwal, pada script tersebut mendefinisikan data pemesanan (booking) yang sudah ada di sistem dan jadwal (schedule) yang tersedia
   "@Given("^the following bookings exist:$")"
   
3. Selanjutnya mencoba memesan slot yang tersedia dalam jadwal . Jika slot tersedia dan tidak ada double booking, berarti berhasil melakukan pemesanan.
   "@When("^I try to book a slot$")"

4. Selanjutnya melakukan validasi apakah pesan konfirmasi ditampilkan atau tidak sesuai dengan skenario. Jika berhasil memesan, koneksi WebDriver terputus
   "@Then("^I should (not )?receive a confirmation$")"
