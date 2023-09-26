package com.m21290940.myfirstapplication.usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.m21290940.myfirstapplication.R;
import com.m21290940.myfirstapplication.usuario.model.Usuario;
import com.m21290940.myfirstapplication.usuario.repository.UsuarioRepository;

public class DashboardUsuario extends AppCompatActivity {
    private UsuarioRepository ur;
    private Usuario userInfo;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_usuario);
        //Aquí creamos la instancia del Usuario repository
        ur = UsuarioRepository.getInstance();
        //Esta es la manera en que recibimos información de otro activity
        String usuario = getIntent().getStringExtra("usuario");
        String pass = getIntent().getStringExtra("pass");
        //Obtenemos información del usuario logueado
        userInfo = ur.getRegisteredUsers().get(usuario).get(pass);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ShapeableImageView ivUserImage = findViewById(R.id.ivUserImage);
        if ( userInfo.getGenero() == 'h' )
            ivUserImage.setImageResource(R.drawable.human_man);
        else
            ivUserImage.setImageResource(R.drawable.human_woman);


        TextView tvUserUsuario = findViewById(R.id.tvUserUsuario);
        TextView tvUserNombre = findViewById(R.id.tvUserNombre);
        TextView tvUserEmail = findViewById(R.id.tvUserEmail);
        TextView tvUserEdad = findViewById(R.id.tvUserEdad);

        tvUserUsuario.setText( userInfo.getUsuario() );
        tvUserNombre .setText( userInfo.getNombre() );
        tvUserEmail  .setText( userInfo.getEmail() );
        tvUserEdad   .setText( userInfo.getEdad() + " ".concat( getString(R.string.tvUserEdadComplement) ) );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tollbar_menu, menu);

        return true;
    }


}