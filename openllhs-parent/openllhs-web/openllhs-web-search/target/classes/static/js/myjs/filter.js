showFilter = function () {

    $("#showF").show();
    $("#hideF").hide();
    $("#filters").show(500);
    $("#result").removeClass("col-md-12");
    $("#result").addClass("col-md-9")
};

hideFilter = function () {

    $("#showF").hide();
    $("#hideF").show();
    $("#filters").hide(500);

    $("#result").removeClass("col-md-9");
    $("#result").addClass("col-md-12")
};


$('#trait_select2').select2();

$('#description_select2').select2({
    minimumInputLength: 1,
    ajax: {
        url: '/hpc/browse/description_list',
        data: function (params) {
            var query = {
                term: params.term
            };
            return query;
        },
        cache: false
    }
});

$('#continent_select2').select2({
    minimumInputLength: 1,
    ajax: {
        url: '/hpc/browse/continent_list',
        data: function (params) {
            var query = {
                term: params.term
            };
            return query;
        },
        cache: false
    }
});

$('#population_select2').select2({
    minimumInputLength: 1,
    ajax: {
        url: '/hpc/browse/population_list',
        data: function (params) {
            var query = {
                term: params.term
            };
            return query;
        },
        cache: false
    }
});

$('#chr_select2').select2();

$('#gene_select2').select2({
    minimumInputLength: 1,
    ajax: {
        url: '/hpc/browse/gene_list',
        data: function (params) {
            var query = {
                term: params.term
            };
            return query;
        },
        cache: false
    }
});

$('#snp_select2').select2({
    minimumInputLength: 1,
    ajax: {
        url: '/hpc/browse/snp_list',
        data: function (params) {
            var query = {
                term: params.term
            };
            return query;
        },
        cache: false
    }
});

$('#author_select2').select2({
    minimumInputLength: 1,
    ajax: {
        url: '/hpc/browse/author_list',
        data: function (params) {
            var query = {
                term: params.term
            };
            return query;
        },
        cache: false
    }
});

$('#reset').on('click', function() {

    $('#trait_select2').val("").trigger("change");
    $("#description_select2").val("").trigger("change");
    $("#chr_select2").val("").trigger("change");
    $("#gene_select2").val("").trigger("change");
    $("#snp_select2").val("").trigger("change");
    $("#population_select2").val("").trigger("change");
    $("#continent_select2").val("").trigger("change")
    $("#author_select2").val("").trigger("change")
});
