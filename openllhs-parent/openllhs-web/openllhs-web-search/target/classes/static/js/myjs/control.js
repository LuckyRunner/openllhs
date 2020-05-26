var _traitTable;
var _geneTable;
var _snpTable;
var _publicationTable;

$(function () {

    var _traitColumns =[
        {"data": "trait",
            render: function(value,row,index){
                return "<a href='https://bigd.big.ac.cn/hpc/browse?trait=" + value + "' >" + value + "</a>"
            }
        },
        {"data": "state0",
            render: function(value,row,index){
                return "<a href='https://bigd.big.ac.cn/hpc/browse?des=" + value + "' >" + value + "</a>"
            }
        },
        {"data": "continent",
            render: function(value,row,index){
                return "<a href='https://bigd.big.ac.cn/hpc/browse?con=" + value + "' >" + value + "</a>"
            }
        },
        {"data": "region"},
        {"data": "population",
            render: function(value,row,index){
                return "<a href='https://bigd.big.ac.cn/hpc/browse?pop=" + value + "' >" + value + "</a>"
            }
        },
        {"data": "studies"}
    ];

    var _geneColumns =[
        {"data": "gene",
            render: function(value,row,index){
                return "<a href='https://bigd.big.ac.cn/hpc/browse?gene=" + value + "' >" + value + "</a>"
            }
        },
        {"data": "location"},
        {"data": "annotation"},
        {"data": "genomeassembly"},
        {"data": "functionalconsequences"}
    ];
    var _snpColumns =[
        {"data": "rsID",
            render: function(value,row,index){
                return "<a href='https://bigd.big.ac.cn/hpc/browse?snp=" + value + "' >" + value + "</a>"
            }
        },
        {"data": "rsID",
            render: function (value, type, row) {
                return '<a name="view_map" data-id='+value+'><i class="fa fa-globe"></i></a>'
            }
        },
        {"data": "location"},
        {"data": "pvalue"},
        {"data": "refallele"},
        {"data": "altallele"},
        {"data": "maf"},
        {"data": "effectvalue"}
    ];

    var _publicationColumns =[
        {"data": "pmid",
            render: function (data, type, row) {
                var list_pmid = row.pmid.split(";");
                var str = '';
                for(var i=0;i<list_pmid.length;i++){
                    str += "<a href='https://www.ncbi.nlm.nih.gov/pubmed/?term=" + list_pmid[i] + "'target=\'_blank\'>" + list_pmid[i] + "</a><br>"
                }
                return str;
            }
        },
        {"data": "reference"},
        {"data": "author"},
        {"data": "magazine"}
    ];

    _traitTable = App.initTraitTables("/hpc/browse/trait", _traitColumns);
    _geneTable = App.initGeneTables("/hpc/browse/gene", _geneColumns);
    _snpTable = App.initTSnpTables("/hpc/browse/snp", _snpColumns);
    _publicationTable = App.initPublicationTables("/hpc/browse/publication", _publicationColumns);

});

function search() {

    var trait = $("#trait_select2").val();
    var state0 = $("#description_select2").val();
    var chromosome = $("#chr_select2").val();
    var gene = $("#gene_select2").val();
    var rsID = $("#snp_select2").val();
    var population = $("#population_select2").val();
    var continent = $("#continent_select2").val();
    var author = $("#author_select2").val();

    var param = {
        "trait": trait,
        "state0": state0,
        "chromosome": chromosome,
        "gene": gene,
        "rsID": rsID,
        "population": population,
        "continent": continent,
        "author": author
    };

    _traitTable.settings()[0].ajax.data = param;
    // _traitTable.ajax.reload(function ( data ) {
    //     $("#trait_count").text('('+data.traitTotal+')');
    // });
    _traitTable.ajax.reload();

    _geneTable.settings()[0].ajax.data = param;
    _geneTable.ajax.reload();

    _snpTable.settings()[0].ajax.data = param;
    _snpTable.ajax.reload();

    _publicationTable.settings()[0].ajax.data = param;
    _publicationTable.ajax.reload();
}

