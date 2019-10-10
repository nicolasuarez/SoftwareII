<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>




<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="https://www.usergioarboleda.edu.co/wp-content/uploads/2017/03/cropped-U-SERGIOV11-290x220.png?1dbd99">
<title>Usapp - Registrate</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<script>
    var datos;

//Verificar El Email




    function procesarOperacion(valOp, idCam, id) {
        console.log(valOp);


        if (valOp === 'GU') {
            var txtValOpe = valOp;
            var exampleFirstName = $("#exampleFirstName").val();
            var exampleLastName = $("#exampleLastName").val();
            var exampleInputId = $("#exampleInputId").val();
            var exampleInputEmail = $("#exampleInputEmail").val();
            var exampleInputUser = $("#exampleInputUser").val();
            var exampleInputPassword = $("#exampleInputPassword").val();
            var exampleRepeatPassword = $("#exampleRepeatPassword").val();
            var dia = $("#dia").val();
            var mes = $("#mes").val();
            var año = $("#año").val();
        }
        var Valido = true;

        $(document).on(function () {
            $("#exampleInputUser").blur(function () {
                var valor = exampleInputUser;
                var attr = " exampleInputUser";
                Valido = false;
                $.ajax({

                    url: "/Software/Register",
                    type: 'GET',
                    dataType: "json",
                    data: {
                        valor: valor,
                        attr: attr
                    },
                    success: function (a) {
                        Valido = a.Email;

                        console.log(Valido);
                        if (!Valido) {
                            $("#exampleInputUser").val(null);
                            $("#exampleInputUser").val().attr("placeholder", "Email no valido");

                        }
                    },
                    error: function () {
                        console.log("No se ha podido obtener la información");
                    }
                });
            });
        });
        console.log(Valido);
        if (Valido) {
            $.ajax({
                url: "/Software/Register",
                type: 'POST',
                data: {
                    txtValOpe: txtValOpe,
                    exampleFirstName: exampleFirstName,
                    exampleLastName: exampleLastName,
                    exampleInputId: exampleInputId,
                    exampleInputEmail: exampleInputEmail,
                    exampleInputUser: exampleInputUser,
                    exampleInputPassword: exampleInputPassword,
                    dia: dia,
                    mes: mes,
                    año: año
                },
                succes: function (data) {
                    console.log(data);
                    location.href = "./login.jsp";

                },
                complete: function (data) {
                    console.log(data);
                    //    location.href = "./login.jsp";
                }
            });
        }

    }




</script>







<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h2 text-gray-900 mb-4">Crea una cuenta</h1>
                            </div>
                            <form class="user">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="exampleFirstName" placeholder="Nombre">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control form-control-user" id="exampleLastName" placeholder="Apellido">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="exampleInputId" placeholder="Id">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="exampleInputUser" placeholder="User">
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control form-control-user" id="exampleInputEmail" placeholder="Email Address">
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Password">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user" id="exampleRepeatPassword" placeholder="Repeat Password">
                                    </div>
                                </div>
                            </form>


                            <form>
                                <div class="text-Left">
                                    <h1 class="h5 text-gray-800 mb-3">Fecha de nacimiento</h1>
                                </div>

                                <div class="form-group col-md-2">
                                    <select id="dia" class="form-control">
                                        <option selected>Día</option>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                        <option>8</option>
                                        <option>9</option>
                                        <option>10</option>
                                        <option>11</option>
                                        <option>12</option>
                                        <option>13</option>
                                        <option>14</option>
                                        <option>15</option>
                                        <option>16</option>
                                        <option>17</option>
                                        <option>18</option>
                                        <option>19</option>
                                        <option>20</option>
                                        <option>21</option>
                                        <option>22</option>
                                        <option>23</option>
                                        <option>24</option>
                                        <option>25</option>
                                        <option>26</option>
                                        <option>27</option>
                                        <option>28</option>
                                        <option>29</option>
                                        <option>30</option>
                                        <option>31</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-2">
                                    <select id="mes" class="form-control">
                                        <option selected>Mes</option>
                                        <option>enero</option>
                                        <option>febrero</option>
                                        <option>marzo</option>
                                        <option>abril</option>
                                        <option>mayo</option>
                                        <option>junio</option>
                                        <option>julio</option>
                                        <option>agosto</option>
                                        <option>septiembre</option>
                                        <option>octubre</option>
                                        <option>noviembre</option>
                                        <option>diciembre</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-2">
                                    <select id="año" class="form-control">
                                        <option selected>Año</option>
                                        <option>1990</option>
                                        <option>1991</option>
                                        <option>1992</option>
                                        <option>1993</option>
                                        <option>1994</option>
                                        <option>1995</option>
                                        <option>1996</option>
                                        <option>1997</option>
                                        <option>1998</option>
                                        <option>1999</option>
                                        <option>2000</option>
                                        <option>2001</option>
                                        <option>2002</option>
                                        <option>2003</option>
                                        <option>2004</option>
                                        <option>2005</option>
                                        <option>2006</option>
                                        <option>2007</option>
                                        <option>2008</option>
                                        <option>2009</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                </div>

                            </form>
                            <form>
                                <div class="text-Left">
                                    <h1 class="h5 text-gray-800 mb-3">Sexo</h1>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" id="gridCheck">
                                            <label class="form-check-label" for="gridCheck">
                                                Mujer
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" id="gridCheck">
                                            <label class="form-check-label" for="gridCheck">
                                                Hombre
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" id="gridCheck">
                                            <label class="form-check-label" for="gridCheck">
                                                Personalizado
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form class="user">

                                <a  class="btn btn-primary btn-user btn-block" id="mostrar"  onclick="procesarOperacion('GU', 'txtValOpe')">
                                    Register Account
                                </a>
                        </div>
                        </form>

                        <hr>
                        <div class="text-center">
                            <a class="small" href="login.jsp">Ya tienes una cuenta? Inicia sesión!</a>
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

