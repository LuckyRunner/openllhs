var App = function () {

    var handlerInitTraitTables = function (url, columns) {
        var _traitTable = $("#trait_table").DataTable({
            "paging":true,//是否假分页
            "info":true,//左下角的信息
            "lengthChange":false,//左上角的每页显示数目
            "ordering":false,//是否允许排序
            "searching":false,//是否允许搜索
            "serverSide":true,//是否开启服务器模式？由服务器做分页功能
            "deferRender":true,//延迟渲染，提高初始化速度
            'autoWidth'   : false,
            "ajax": {
                "url": url
            },
            "columns": columns
            });
        return _traitTable;
    };

    var handlerInitGeneTables = function (url, columns) {
        var _geneTable = $("#gene_table").DataTable({
            "paging":true,//是否假分页
            "info":true,//左下角的信息
            "lengthChange":false,//左上角的每页显示数目
            "ordering":false,//是否允许排序
            "searching":false,//是否允许搜索
            "serverSide":true,//是否开启服务器模式？由服务器做分页功能
            "deferRender":true,//延迟渲染，提高初始化速度
            'autoWidth'   : false,
            "ajax": {
                "url": url
            },
            "columns": columns

        });
        return _geneTable;
    };

    var handlerInitSnpTables = function (url, columns) {
        var _snpTable = $("#snp_table").DataTable({
            "paging":true,//是否假分页
            "info":true,//左下角的信息
            "lengthChange":false,//左上角的每页显示数目
            "ordering":false,//是否允许排序
            "searching":false,//是否允许搜索
            "serverSide":true,//是否开启服务器模式？由服务器做分页功能
            "deferRender":true,//延迟渲染，提高初始化速度
            'autoWidth'   : false,
            "ajax": {
                "url": url
            },
            "columns": columns

        });
        return _snpTable;
    };

    var handlerInitPublicationTables = function (url, columns) {
        var _publicationTable = $("#publication_table").DataTable({
            "paging":true,//是否假分页
            "info":true,//左下角的信息
            "lengthChange":false,//左上角的每页显示数目
            "ordering":false,//是否允许排序
            "searching":false,//是否允许搜索
            "serverSide":true,//是否开启服务器模式？由服务器做分页功能
            "deferRender":true,//延迟渲染，提高初始化速度
            'autoWidth'   : false,
            "ajax": {
                "url": url
            },
            "columns": columns

        });
        return _publicationTable;
    };



    return {

        initTraitTables: function (url, columns) {
            return handlerInitTraitTables(url, columns);
        },

        initGeneTables: function (url, columns) {
            return handlerInitGeneTables(url, columns);
        },

        initTSnpTables: function (url, columns) {
            return handlerInitSnpTables(url, columns);
        },

        initPublicationTables: function (url, columns) {
            return handlerInitPublicationTables(url, columns);
        }


    }
}();
