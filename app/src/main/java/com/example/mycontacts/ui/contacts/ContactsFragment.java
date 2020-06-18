package com.example.mycontacts.ui.contacts;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.mycontacts.R;
import com.example.mycontacts.entity.Contact;
import com.example.mycontacts.entity.ContactList;
import com.example.mycontacts.persistence.DBController;
import com.example.mycontacts.persistence.DBFactory;
import com.example.mycontacts.retrofit.RetrofitInicializer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsFragment extends Fragment {

    private ContactsViewModel contactsViewModel;
    private List<Contact> contacts;
    private String filter = "";
    private DBController db;
    private ListView list;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactsViewModel =
                ViewModelProviders.of(this).get(ContactsViewModel.class);
        root = inflater.inflate(R.layout.fragment_contacts, container, false);

        db = new DBController(getContext());

        getInfo();
        return root;
    }

    public void getInfo(){
        contacts = new ArrayList<>();
        Call<ContactList> call = new RetrofitInicializer().getApi().listContacts();
        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                assert response.body() != null;
                contacts = response.body().getData();
                saveOnDatabasa();
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
                Log.e("Lista de Contatos", "Erro ao listar os contatos.");
                setLayout();
            }
        });
    }

    public void saveOnDatabasa(){
        for(Contact c : contacts){
            db.insertContact(c);
        }
        setLayout();
    }

    public void setLayout(){
        Cursor cursor = db.listContacts(filter);

        String[] fields = new String[] {DBFactory.CONTACT_NAME, DBFactory.CONTACT_COMPANY, DBFactory.CONTACT_PHOTO, DBFactory.CONTACT_NEW};
        int[] ids = new int[] {R.id.name, R.id.company, R.id.image, R.id.new_contact};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getContext(),
                R.layout.contact_adapter,cursor,fields,ids, 0);
        list = (ListView) root.findViewById(R.id.listView);
        list.setAdapter(adaptador);
    }

}