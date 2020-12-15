<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../partials/head.ftlh">

    <title>Books Create</title>
</head>

<body>
<#include "../partials/navbar.ftlh">
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <#include "../partials/sidebar.ftlh">
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <form class="form-horizontal" action="/books" method="POST">
                <h1 class="page-header">Create Book</h1>

                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="form-group">
                        <label for="productCode">Title</label>
                        <@spring.formInput path="bookForm.title" attributes="class='form-control'" />
                        <@spring.showErrors "<br>"/>
                    </div>
                    <div class="form-group">
                        <label for="productName">Year</label>
                        <@spring.formInput path="bookForm.year" attributes="class='form-control'"/>
                        <@spring.showErrors "<br>"/>
                    </div>
                </div>

                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 pull-right">
                    <button type="submit" class="btn btn-success">Save</button>
                    <button type="reset" id="btn-clear" class="btn btn-danger">Clear</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
