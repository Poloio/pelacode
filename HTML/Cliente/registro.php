<?php
	require('dbconnect.php');
    // If the values are posted, insert them into the database.
    if (isset($_POST['nombre']) && isset($_POST['password'])){
        $nombre = $_POST['nombre'];
	$email = $_POST['email'];
        $password = md5($_POST['password']);
 
        $query = "INSERT INTO `usuarios` (nombre, password, correo) VALUES ('$nombre', '$password', '$email')";
        $result = mysqli_query($conn, $query);
        if($result){
            $smsg = "User Created Successfully.";
        }else{
            $fmsg ="User Registration Failed";
        }
    }
?>

<html>

<head>
    <title>Iniciar Sesión</title>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.0/css/bootstrap.min.css"
        integrity="sha384-SI27wrMjH3ZZ89r4o+fGIJtnzkAnFs3E4qz9DIYioCQ5l9Rd/7UAa8DHcaL8jkWt" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="style.css">

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>

    <script src="https://cd n.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.0/js/bootstrap.min.js"
        integrity="sha384-3qaqj0lc6sV/qpzrc1N5DC6i1VRn/HyX4qdPaiEFbn54VjQBEU341pvjz7Dv3n6P"
        crossorigin="anonymous"></script>

    <script src="https://kit.fontawesome.com/bef86badea.js" crossorigin="anonymous"></script>

    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>

<body>
    <div class="contenedor vh-100 ">
	<div id="entrada" class="container shadow-lg p-3 ">
		<h1>Únete a nosotros</h1>
        <div class="row pt-4 pl-5">
            <div class="col-4 align-left">
                <form class="needs-validation" method="post" action="registro.php" novalidate>
                    <div class="form-group">
                        <label for="exampleInputnombre">Nombre de Usuario</label>
                        <input type="text" class="form-control" name="nombre" id="nombre" required>
                        <div class="invalid-feedback">
                            Debes introducir un nombre de usuario.
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="exampleInputEmail">Correo electrónico</label>
                        <input type="email" class="form-control" name="email" id="email" required>
                        <div class="invalid-feedback">
                            Debes introducir un email válido.
                        </div>
                    </div>
                
                    <div class="form-group">
                        <label for="exampleInputPassword">Contraseña</label>
                        <input type="password" class="form-control" name="password" id="password" required>
                        <div class="invalid-feedback">
                            Debes introducir una contraseña.
                        </div>
                    </div>
                    <br>
                    <div class="g-recaptcha" data-sitekey="6LclCUIUAAAAABx5sLCjoRBXsM-c-K4HpdP4Ma51" required></div>
                    <br>
                    <button type="submit" class="btn btn-light btnform">¡Vamos!</button>
                </form>

                <script>
                // Example starter JavaScript for disabling form submissions if there are invalid fields
                (function() {
                'use strict';
                window.addEventListener('load', function() {
                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                    var forms = document.getElementsByClassName('needs-validation');
                    // Loop over them and prevent submission
                    var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                    });
                }, false);
                })();
                </script>
            </div>
            
            <div class="col-8 pt-4">
            	<p class="pt-4 h4">
           		Ayudas a crecer a una pequeña iniciativa que nos<br>
           		hace mucha ilusión, ¡muchas gracias!
            	</p>
            	<img src="hqdefault.jpg" alt="fotoperrito" height="50%" width="50%">
            </div>
        </div>
    </div>

</body>

</html>