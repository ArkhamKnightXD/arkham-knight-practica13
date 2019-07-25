
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
            <strong>Editar componente</strong>
        </h1>

        <br>
    </section>


    <form method="post" class="form-horizontal" action="/componente/editar/?id=${componente.id}">
        <div class="row">

            <div class="form-group">
                <label for="descripcion" class="control-label col-md-3">Descripcion del componente:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="descripcion" value="${componente.descripcion}" class="form-control" required placeholder="Descripcion...">
                </div>

            </div>



            <div class="form-group">
                <label for="codigoAlmacen" class="control-label col-md-3">Codigo del almacen:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="codigoAlmacen" value="${componente.codigoAlmacen}" class="form-control " placeholder="Codigo del almacen...">
                </div>

            </div>



            <div class="form-group">
                <label for="stock" class="control-label col-md-3">Balance actual:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="number" name="stock" value="${componente.stock}" class="form-control" required placeholder="Stock...">
                </div>

            </div>


            <div class="form-group">
                <label for="precioUnidadCompra" class="control-label col-md-3">Precio por unidad:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="precioUnidadCompra" value="${componente.precioUnidadCompra}" class="form-control" placeholder="Precio por unidad...">
                </div>

            </div>



            <div class="form-group">
                <button class="btn btn-primary col-md-offset-5" type="submit">Guardar</button>
                <a class="btn btn-danger" href="/componente/" role="button">Cancelar</a>
            </div>


        </div>


    </form>


</div>

</body>
</html>

