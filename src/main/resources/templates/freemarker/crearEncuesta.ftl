
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <title> ${titulo}</title>

    <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../dist/css/AdminLTE.min.css">

    <link rel="stylesheet" href="../../style/style.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
    <link rel="stylesheet" href="../../dist/css/skins/skin-blue.min.css">

</head>
<body background="../../pictures/fondo.png">


<div class="container-form center">

    <section class="content-header">
        <h1 class="text-center">
            <strong>Realice la encuesta sobre las charlas</strong>
        </h1>

        <br>
    </section>


    <form method="post" class="form-horizontal" action="/crear">
        <div class="row">

            <div class="form-group">
                <label for="cumplieronExpectativas" class="control-label col-md-3">Las charlas cumplieron sus expectativas?</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">

                    <select class="form-control" name="cumplieronExpectativas" required>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>

            </div>



            <div class="form-group">
                <label for="dominioDelTema" class="control-label col-md-3">Los Expositores demostraron dominio del tema?</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <select class="form-control" name="dominioDelTema" required>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>

            </div>



            <div class="form-group">
                <label for="instalacionesConfortables" class="control-label col-md-3">Las instalaciones eran confortables:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <select class="form-control" name="instalacionesConfortables" required>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>

                </div>

            </div>


            <div class="form-group">
                <label for="comentario" class="control-label col-md-3">Algun comentario</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="comentario" class="form-control" placeholder="Algun Comentario...">
                </div>

            </div>



            <div class="form-group">
                <button class="btn btn-primary col-md-offset-3" type="submit">Enviar</button>
            </div>


        </div>


    </form>


</div>

</body>
</html>

