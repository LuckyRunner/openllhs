$(function () {
    $('#all_search').select2({
        minimumInputLength: 1,
        ajax: {
            url: '/hpc/all_list',
            data: function (params) {
                var query = {
                    term: params.term
                };
                return query;
            },
            cache: false
        },
        templateResult: formatState
    });

    $('#all_search').on('select2:select', function (e) {

        var term = $("#all_search").val();

        if (term.split("|")[1] == "Trait") {
            $(location).attr('href', 'https://bigd.big.ac.cn/hpc/browse?trait='+ term.split("|")[0]);
        }
        if (term.split("|")[1] == "Gene") {
            $(location).attr('href', 'https://bigd.big.ac.cn/hpc/browse?gene='+ term.split("|")[0]);
        }
        if (term.split("|")[1] == "SNP") {
            $(location).attr('href', 'https://bigd.big.ac.cn/hpc/browse?snp='+ term.split("|")[0]);
        }
        if (term.split("|")[1] == "Population") {
            $(location).attr('href', 'https://bigd.big.ac.cn/hpc/browse?pop='+ term.split("|")[0]);
        }
        if (term.split("|")[1] == "Continent") {
            $(location).attr('href', 'https://bigd.big.ac.cn/hpc/browse?con='+ term.split("|")[0]);
        }
        if (term.split("|")[1] == "Description") {
            $(location).attr('href', 'https://bigd.big.ac.cn/hpc/browse?des='+ term.split("|")[0]);
        }
        if (term.split("|")[1] == "Chromosome") {
            $(location).attr('href', 'https://bigd.big.ac.cn/hpc/browse?chr='+ term.split("|")[0]);
        }
    });

    selectTab()
});

function formatState (state) {
    var $state = $("<span>" + state.text.split("|")[0]+"</span><span style='float: right'><kbd>" + state.text.split("|")[1]+"</kbd></span>");
    return $state;
}

String.prototype.replaceAll = function (FindText, RepText) {
    regExp = new RegExp(FindText, "g");
    return this.replace(regExp, RepText);
};

function selectTab() {

    a = decodeURI(window.location.href);

    if (a.search("gene=") != -1) {
        var reg = /gene=(.*)$/ig;
        var b = reg.exec(a);
        var newOption = new Option(b[1], b[1], true, true);
        $('#gene_select2').append(newOption).trigger('change');
        return null;
    }
    if (a.search("trait=") != -1) {
        var reg = /trait=(.*)$/ig;
        var b = reg.exec(a);
        var newOption = new Option(b[1], b[1], true, true);
        $('#trait_select2').append(newOption).trigger('change');
        return null;
    }
    if (a.search("snp=") != -1) {
        var reg = /snp=(.*)$/ig;
        var b = reg.exec(a);
        var newOption = new Option(b[1], b[1], true, true);
        $('#snp_select2').append(newOption).trigger('change');
        return null;
    }
    if (a.search("pop=") != -1) {
        var reg = /pop=(.*)$/ig;
        var b = reg.exec(a);
        var newOption = new Option(b[1], b[1], true, true);
        $('#population_select2').append(newOption).trigger('change');
        return null;
    }
    if (a.search("con=") != -1) {
        var reg = /con=(.*)$/ig;
        var b = reg.exec(a);
        var newOption = new Option(b[1], b[1], true, true);
        $('#continent_select2').append(newOption).trigger('change');
        return null;
    }
    if (a.search("des=") != -1) {
        var reg = /des=(.*)$/ig;
        var b = reg.exec(a);
        var newOption = new Option(b[1], b[1], true, true);
        $('#description_select2').append(newOption).trigger('change');
        return null;
    }
    if (a.search("chr=") != -1) {
        var reg = /chr=(.*)$/ig;
        var b = reg.exec(a);
        var newOption = new Option(b[1], b[1], true, true);
        $('#chr_select2').append(newOption).trigger('change');
        return null;
    }
    if (a.search("author=") != -1) {
        var reg = /author=(.*)$/ig;
        var b = reg.exec(a);
        console.log(reg.exec(a));
        var newOption = new Option(b[1], b[1], true, true);
        $('#author_select2').append(newOption).trigger('change');
        return null;
    }

}


