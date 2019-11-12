<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<<<<<<< HEAD
<<<<<<< HEAD
   <html lang="en">
=======
<html lang="en">
>>>>>>> Marcocastellanos
=======
<html lang="en">
>>>>>>> Marcocastellanos



    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="https://www.usergioarboleda.edu.co/wp-content/uploads/2017/03/cropped-U-SERGIOV11-290x220.png?1dbd99">
    <title>Usapp - Inicia sesión</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/skel/3.0.1/skel.min.js">
    </script>
    <script>
        $(document).on("click", "#botonIniciarSesion", function () {
            var correo = $("#exampleInputEmail").val();
            var password = $("#exampleInputPassword").val();


            console.log(correo);
            $.ajax({
                url: "Login",
                type: 'POST',
                dataType: "json",
                data: {
                    correo: correo,
                    password: password
                },
                success: function (response) {
                    validacion = response.validacion;
                    mensaje = response.mensaje;
                    tipoUsuario = response.tipoUsuario;
                    correo = response.correo;
                    idUsuario = response.idUsuario;
                    if (validacion === "Inicio de sesion valido") {
                        if (tipoUsuario === "Estudiante") {
                            window.location.replace("./dashboard.jsp");
                        } else {
                            window.location.replace("./PerfilPropietario.jsp");
                        }
                    }
                    if (validacion === "Inicio de sesion no valido") {
                        $("#exampleInputEmail").val(null);
                        $("#exampleInputPassword").val(null);
                        if (mensaje === "El correo no es valido") {
                            alert(validacion + ": " + mensaje);
                        }
                        if (mensaje === "La clave y el usuario no corresponden") {
                            alert(validacion + ": " + mensaje);
                        }
                    }
                },
                error: function (err) {
                    console.log(err);
                    alert("Hola, No podemos Atederte por el momento, lo sentimos");
                }
            });
        });
<<<<<<< HEAD
<<<<<<< HEAD
        
        
=======
>>>>>>> Marcocastellanos
=======
>>>>>>> Marcocastellanos
    </script>


    <body class="bg-gradient-primary">

        <div class="container">

            <!-- Outer Row -->
            <div class="row justify-content-center">

                <div class="col-xl-10 col-lg-12 col-md-9">

                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                                <div class="col-lg-6">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Para continuar, inicia sesión en USAPP.</h1>
                                        </div>
                                        <form class="user">
                                            <div class="form-group">
                                                <input type="email" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Dirección de correo o nombre de usuario">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Contraseña">
                                            </div>
                                            <div class="form-group">
                                                <div class="custom-control custom-checkbox small">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck">
                                                    <label class="custom-control-label" for="customCheck">Recuérdame</label>
                                                </div>
                                            </div>
                                            <a  class="btn btn-primary btn-user btn-block"  id="botonIniciarSesion">
                                                Acceder
                                            </a>
                                            <div class="text-center">
                                                <a class="small" href="forgot-password.jsp">¿Se te ha olvidado la contraseña?</a>
                                            </div>
                                        </form>
                                        <hr>
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">¿No tienes cuenta?</h1>
                                        </div>
                                        <div class="text-center">
                                            <a class="small" href="register.jsp">Registrate a Usapp</a>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

    </body>

</html>