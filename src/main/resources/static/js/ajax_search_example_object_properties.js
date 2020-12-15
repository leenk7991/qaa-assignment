(function ($) {

    $(document).ready(function () {

        var API_URL = '/api/books';
        var error_message = 'There are no matches available.';
        var $tableSelector = $('#searchResultsProperties');
        var $searchForm = $('#searchForm');

        var $tableBody = $tableSelector.find('tbody');

        function getItems(success, error) {
            $.ajax({
                url: API_URL,
                method: 'GET',
                data: $searchForm.serialize()
            }).done(function (response) {
                success(response);
            }).fail(function () {
                error();
            });

        }

        function renderRowErrorMessage() {
            var $row = $('<tr></tr>');
            var $cell = $('<td></td>').text(error_message);
            $row.append($cell);
            $tableBody.append($row);
        }

        function renderTable(results) {


            results.forEach(function (item) {
                var $tr = $('<tr></tr>');

                var $cellAuthor = $('<td>' + item.author + '</td>');
                var $cellTitle = $('<td>' + item.title + '</td>');
                var $cellYear = $('<td>' + item.year + '</td>');

                var $cellEditButton = $('<td><a class="btn btn-success" href="/books/' + item.id + '" title="Edit">Edit</a></td>');
                var $cellDeleteButton = $('<td><a class="btn btn-danger" href="/books/' + item.id + '" title="Delete">Delete</a></td>');

                $tr.append($cellAuthor);
                $tr.append($cellTitle);
                $tr.append($cellYear);
                $tr.append($cellEditButton);
                $tr.append($cellDeleteButton);

                $tableBody.append($tr);

            });

            $tableSelector.removeClass('hidden');
        }

        if ($tableSelector.length > 0) {
            $searchForm.submit(function (e) {

                e.preventDefault();

                getItems(function (listOfItems) {
                    renderTable(listOfItems);
                }, function () {
                    renderRowErrorMessage();
                });

            });
        }

    });

})(jQuery);