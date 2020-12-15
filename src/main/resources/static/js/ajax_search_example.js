(function ($) {

    $(document).ready(function () {

        var API_URL = '/api/books';
        var error_message = 'There are no matches available.';
        var $tableSelector = $('#searchResults');
        var $searchForm = $('#searchForm');

        var $tableBody = $tableSelector.find('tbody');

        function getItems(success, error) {
            $.ajax({
                url: API_URL,
                method: 'GET',
                data: $searchForm.serialize()
            }).done(function (response) {
                success(response);
            }).fail(function (response) {
                error();
                console.log(response);
            });

        }

        function renderRowErrorMessage() {
            var $row = $('<tr></tr>');
            var $cell = $('<td></td>').text(error_message);
            $row.append($cell);
            $tableBody.append($row);
        }

        function renderTable(items) {

            items.forEach(function (item) {
                var $tr = $('<tr></tr>');

                Object.values(item).forEach(function (value) {
                    $tr.append('<td>' + value + '</td>');
                });

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