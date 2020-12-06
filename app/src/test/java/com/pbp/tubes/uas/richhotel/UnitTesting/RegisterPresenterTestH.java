package com.pbp.tubes.uas.richhotel.UnitTesting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterPresenterTestH {
    @Mock
    private RegisterView view;
    @Mock
    private RegisterService service;
    private RegisterPresenter presenter;
    @Before
    public void setUp() throws Exception {
        presenter = new RegisterPresenter(view, service);
    }
    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty() throws Exception {
        System.out.println("Test 1 Email Kosong");
        when(view.getEmail()).thenReturn("");
        System.out.println("email : "+view.getEmail());
        presenter.onRegisterClicked();
        verify(view).showEmailError("Email tidak boleh kosong");
    }
    @Test

    public void shouldShowErrorMessageWhenEmailAreinvalid() throws Exception {
        System.out.println("Test 2 Email Invalid");
        when(view.getEmail()).thenReturn("yosia");
        System.out.println("email : "+view.getEmail());
        presenter.onRegisterClicked();
        verify(view).showEmailError("Email tidak valid");
    }


    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
        System.out.println("Test 3 Password Kosong");
        when(view.getEmail()).thenReturn("yosiagalih02@gmail.com");
        System.out.println("email : "+ view.getEmail());
        when(view.getPassword()).thenReturn("");
        System.out.println("password : "+view.getPassword());
        presenter.onRegisterClicked();
        verify(view).showPasswordError("Password tidak boleh kosong");
    }

    @Test
    public void shouldShowLoginErrorWhenPasswordAreInvalid() throws
            Exception {
        System.out.println("Test 4 Password invalid");
        when(view.getEmail()).thenReturn("yosiagalih02@gmail.com");
        System.out.println("email : "+view.getEmail());
        when(view.getPassword()).thenReturn("1234");
        System.out.println("password : "+view.getPassword());
        presenter.onRegisterClicked();
        verify(view).showPasswordError("Password tidak boleh kurang dari 6 karakter");
    }

    @Test
    public void shouldShowErrorMessageWhenNamaIsEmpty() throws Exception {
        System.out.println("Test 5 Nama Kosong");
        when(view.getEmail()).thenReturn("yosiagalih02@gmail.com");
        System.out.println("email : "+ view.getEmail());
        when(view.getPassword()).thenReturn("123456");
        System.out.println("password : "+view.getPassword());
        when(view.getName()).thenReturn("");
        System.out.println("nama : "+view.getName());
        presenter.onRegisterClicked();
        verify(view).showNameError("Nama tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenAgeIsEmpty() throws Exception {
        System.out.println("Test 6 Age Kosong");
        when(view.getEmail()).thenReturn("yosiagalih02@gmail.com");
        System.out.println("email : "+ view.getEmail());
        when(view.getPassword()).thenReturn("123456");
        System.out.println("password : "+view.getPassword());
        when(view.getName()).thenReturn("Yosia Galih");
        System.out.println("nama : "+view.getName());
        when(view.getAge()).thenReturn("");
        System.out.println("age : "+view.getAge());
        presenter.onRegisterClicked();
        verify(view).showAgeError("Age tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenAllInputRegisterCorrect() throws Exception {
        System.out.println("Test 7 Register Benar");
        when(view.getEmail()).thenReturn("yosiagalih02@gmail.com");
        System.out.println("email : "+ view.getEmail());
        when(view.getPassword()).thenReturn("123456");
        System.out.println("password : "+view.getPassword());
        when(view.getName()).thenReturn("Yosia Galih");
        System.out.println("nama : "+view.getName());
        when(view.getAge()).thenReturn("21");
        System.out.println("alamat : "+view.getAge());
        when(service.getValid(view, view.getEmail(), view.getPassword(), view.getName(),
                view.getAge())).thenReturn(true);
        System.out.println("Hasil : "+service.getValid(view,view.getEmail(),
                view.getPassword(), view.getName(), view.getAge()));
        presenter.onRegisterClicked();
    }
}