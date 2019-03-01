package id.sch.smktelkom.www.tugasmodul2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void increment(View view) {//perintah tombol tambah
        if (quantity == 100) {
            Toast.makeText(this, "pesanan maximal 100", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {//perintah tombol tambah
        if (quantity == 1) {
            Toast.makeText(this, "pesanan minimal 1", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }


    public void Submitorder(View view) {
        EditText nameEditText = findViewById(R.id.edt_name);
        String name = nameEditText.getText().toString();
        Log.v("MainActivity", "Nama:" + name);

        CheckBox NovelChekBox = findViewById(R.id.Novel_checkbox);
        boolean hasNovel = NovelChekBox.isChecked();
        Log.v("MainActivity", "has Novel:" + hasNovel);

        CheckBox KomikChekBox = findViewById(R.id.Komik_checkbox);
        boolean hasKomik = KomikChekBox.isChecked();
        Log.v("MainActivity", "has Novel:" + hasKomik);

        int price = calculateprice(hasNovel, hasKomik);
        String pricemessage = createOrderSummary(price, name, hasNovel, hasKomik);


        displayMessage(pricemessage);

    }

    private int calculateprice(boolean addNovel, boolean addKomik) {//jumlah pesanan * harga
        int harga = 0;

        if (addNovel) {
            harga = harga + 10000;
        }

        if (addKomik) {
            harga = harga + 25000;
        }

        return quantity * harga;
    }

    private String createOrderSummary(int price, String name, boolean addKomik, boolean addNovel) {
        String pricemessage = " Nama = " + name;
        pricemessage += "\n Jumlah " + quantity;
        pricemessage += "\n Total Rp" + price;
        pricemessage += "\n Terima Kasih";
        return pricemessage;
    }

    //method ini untuk mencetak hasil perintah yang di tampilkan dengan inisial quantity_textview di textview 0
    private void displayMessage(String message) {
        TextView priceTextView = findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}