package br.exemplozxingintegration;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final int REQUEST_CODE = 0;
	private TextView txResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txResult = (TextView) findViewById(R.id.txResult);
	}
	
	
	public void leitura(View view){
		/*Metodo para pegar o click do botao e chamar a intent do zxing
		 * 
		 * modificação no com.google.zxing.client.android.Camera.CameraConfigurationManager
		 * linha 78 - Adicionado  camera.setDisplayOrientation(90);
		 * para alinhar a camera na vertical
		 */
		Intent it = new Intent(MainActivity.this, com.google.zxing.client.android.CaptureActivity.class);
		startActivityForResult(it, REQUEST_CODE);
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		/*
		 * Método de retorno do zxing ,
		 * após o scanner do qrcode ele retorna para este metodo
		 * 
		 * para isto fazemos modificação no com.google.zxing.client.android.CaptureActivity
		 * adicionamos as linhas 404 á 407
		 * 
		 * */
		if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
			if(data.getStringExtra("SCAN_FORMAT").equals("QR_CODE")){
			txResult.setText("RESULTADO: "+data.getStringExtra("SCAN_RESULT"));
		}
			else {
				Toast.makeText(this, "QR Code invalido", Toast.LENGTH_LONG).show();
			}
			}
	}
}
