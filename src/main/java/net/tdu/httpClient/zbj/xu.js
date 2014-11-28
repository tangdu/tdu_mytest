function $(A) {
    return document.getElementById(A)
}
$.bom = {query: function (B) {
    var A = window.location.search.match(new RegExp("(/?|&)" + B + "=([^&]*)(&|$)"));
    return !A ? "" : unescape(A[2])
}, getHash: function () {
}};
var pt = {ishttps: false, low_login: 0, keyindex: 9, init: function () {
    pt.ishttps = /^https/.test(window.location);
    if (navigator.mimeTypes["application/nptxsso"]) {
        var B = document.createElement("embed");
        B.type = "application/nptxsso";
        B.style.width = "0px";
        B.style.height = "0px";
        document.body.appendChild(B);
        pt.sso = B
    }
    try {
        if ($.bom.query("low_login") == "1") {
            pt.low_login = 1;
            $("low_login_box").style.display = "block"
        }
    } catch (A) {
    }
    window.setTimeout(function () {
        ptui_reportAttr(256040, 0.05)
    }, 1000)
}, switchLowLogin: function (A) {
    if (A.checked) {
        $("low_login_hour").disabled = ""
    } else {
        $("low_login_hour").disabled = "disabled"
    }
}};
pt.init();
STR_QLOGIN = 1;
STR_QLOGIN_OTHER_ERR = 2;
STR_QLOGIN_SELECT_TIP = 3;
STR_QLOGIN_NO_UIN = 4;
STR_QLOGIN_SELECT_OFFLINE = 5;
STR_QLOGINING = 6;
function ptui_mapStr(B) {
    for (i = 0; i < B.length; i++) {
        var A = $(B[i][1]);
        if (A != null) {
            if ("A" == A.nodeName || "U" == A.nodeName || "OPTION" == A.nodeName || "LABEL" == A.nodeName || "P" == A.nodeName) {
                if (A.innerHTML == "") {
                    A.innerHTML = ptui_str(B[i][0])
                }
            } else {
                if ("INPUT" == A.nodeName) {
                    if (A.value == "") {
                        A.value = ptui_str(B[i][0])
                    }
                } else {
                    if ("IMG" == A.nodeName) {
                        A.alt = ptui_str(B[i][0])
                    }
                }
            }
        }
    }
}
function ptui_str(A) {
    A -= 1;
    if (A >= 0 && A < g_strArray.length) {
        return g_strArray[A]
    }
    return""
}
var g_labelMap = new Array([STR_QLOGIN, "loginbtn"], [STR_QLOGIN_SELECT_TIP, "qlogin_select_tip"]);
ptui_mapStr(g_labelMap);
function getArgs() {
    var B = new Object();
    try {
        var F = location.href.substring(location.href.indexOf("/qlogin?") + 8);
        var E = F.split("&");
        for (var C = 0; C < E.length; C++) {
            var H = E[C].indexOf("=");
            if (H == -1) {
                continue
            }
            var A = E[C].substring(0, H);
            var D = E[C].substring(H + 1);
            D = decodeURIComponent(D);
            B[A] = D
        }
    } catch (G) {
        setTimeout(arguments.callee, 0)
    }
    return B
}
var params = getArgs();
var g_qtarget = params.qtarget;
var g_domain = params.domain;
var g_jumpname = params.jumpname;
var g_param = params.param;
var g_appid = params.appid;
var g_daid = params.daid;
var site = ["qq.com", "paipai.com", "tencent.com", "soso.com", "taotao.com", "tenpay.com", "foxmail.com", "wenwen.com", "3366.com", "imqq.com", "pengyou.com", "qplus.com", "qzone.com", "myapp.com", "kuyoo.cn", "weiyun.com", "wechatapp.com", "51buy.com", "gaopeng.com", "qcloud.com", "qmail.com"];
var flag = false;
for (var i = 0; i < site.length; i++) {
    if (site[i] == g_domain) {
        flag = true
    }
}
if (!flag) {
    g_domain = "qq.com"
}
var q_bInit = false;
var q_hummerQtrl = null;
var g_vOptData = null;
var q_aUinList = new Array();
function suportActive() {
    var A = true;
    try {
        if (window.ActiveXObject || window.ActiveXObject.prototype) {
            A = true
        } else {
            A = false
        }
    } catch (B) {
        A = false
    }
    return A
}
function ptui_qInit() {
    if (q_bInit) {
        return
    }
    q_bInit = true;
    try {
        if (suportActive()) {
            q_hummerQtrl = new ActiveXObject("SSOAxCtrlForPTLogin.SSOForPTLogin2");
            var A = q_hummerQtrl.CreateTXSSOData();
            q_hummerQtrl.InitSSOFPTCtrl(0, A);
            g_vOptData = q_hummerQtrl.CreateTXSSOData()
        } else {
        }
        hummer_loaduin();
        if (q_aUinList.length <= 0) {
            msg(ptui_str(STR_QLOGIN_NO_UIN));
            return false
        } else {
            if (ptui_buildUinList) {
                ptui_buildUinList(q_aUinList)
            }
        }
        document.cookie = "ptui_qstatus=2;domain=ptlogin2." + g_domain + ";path=/"
    } catch (B) {
        q_hummerQtrl = null;
        document.cookie = "ptui_qstatus=3;domain=ptlogin2." + g_domain + ";path=/";
        msg(ptui_str(STR_QLOGIN_OTHER_ERR));
        ptui_reportAttr(89217, 0.05)
    }
}
function list() {
    $("qlogin_loading").style.visibility = "hidden";
    if (/^https/g.test(window.location)) {
        $("qlogin_loading").innerHTML = '<img src="https://xui.ptlogin2.' + g_domain + '/style.ssl/0/images/load.gif" align="absmiddle" />' + ptui_str(STR_QLOGINING)
    } else {
        $("qlogin_loading").innerHTML = '<img src="http://imgcache.qq.com/ptlogin/v4/style/0/images/load.gif" align="absmiddle" />' + ptui_str(STR_QLOGINING)
    }
    q_bInit = false;
    ptui_qInit();
    if (/^https/g.test(window.location)) {
        return
    }
    if (window.g_time) {
        g_time.time55 = new Date()
    }
    xui_report()
}
function ptui_buildUinList() {
    var G = "";
    var E = $("list_uin");
    if (null == E) {
        return
    }
    var A = q_aUinList.length > 5 ? 5 : q_aUinList.length;
    for (var C = 0; C < A; C++) {
        var F = q_aUinList[C];
        var B = "";
        var D = "";
        if (q_aUinList.length == 1) {
            D = 'style="display:none;"'
        }
        if (C == 0) {
            B = "checked='checked'"
        }
        G += "<li><input type='radio' name='q_uin' id='uin_" + F.uin + "' " + B + D + " /><label for='uin_" + F.uin + "'>" + F.nick.replace(/&/g, "&amp").replace(/</g, "&lt").replace(/>/g, "&gt") + "&nbsp;(" + F.name + ")</label></li>"
    }
    E.innerHTML = G
}
function onQloginSelect() {
    for (var C = 0; C < q_aUinList.length; C++) {
        var D = q_aUinList[C];
        var B = $("uin_" + D.uin);
        if (B != null) {
            if (B.checked) {
                hummer_loaduin();
                var A = hummer_getUinObj(D.uin);
                if (A == null) {
                    msg(ptui_str(STR_QLOGIN_SELECT_OFFLINE), D.uin);
                    return
                }
                $("qlogin_loading").style.visibility = "visible";
                $("loginbtn").className = "btn_gray";
                $("loginbtn").style.color = "gray";
                hummer_login(A, g_domain, g_jumpname, g_param)
            }
        }
    }
}
function hummer_loaduin() {
    q_aUinList.length = 0;
    if (suportActive()) {
        var Y = q_hummerQtrl.DoOperation(1, g_vOptData);
        if (null == Y) {
            return
        }
        try {
            var T = Y.GetArray("PTALIST");
            var c = T.GetSize();
            var X = "";
            var H = $("list_uin");
            for (var d = 0; d < c; d++) {
                var E = T.GetData(d);
                var a = E.GetDWord("dwSSO_Account_dwAccountUin");
                var J = "";
                var O = E.GetByte("cSSO_Account_cAccountType");
                var b = a;
                if (O == 1) {
                    try {
                        J = E.GetArray("SSO_Account_AccountValueList");
                        b = J.GetStr(0)
                    } catch (Z) {
                    }
                }
                var Q = 0;
                try {
                    Q = E.GetWord("wSSO_Account_wFaceIndex")
                } catch (Z) {
                    Q = 0
                }
                var S = "";
                try {
                    S = E.GetStr("strSSO_Account_strNickName")
                } catch (Z) {
                    S = ""
                }
                var F = E.GetBuf("bufGTKey_PTLOGIN");
                var G = E.GetBuf("bufST_PTLOGIN");
                var N = "";
                var A = G.GetSize();
                for (var W = 0; W < A; W++) {
                    var B = G.GetAt(W).toString("16");
                    if (B.length == 1) {
                        B = "0" + B
                    }
                    N += B
                }
                var M = {uin: a, name: b, type: O, face: Q, nick: S, key: N};
                q_aUinList[d] = M
            }
        } catch (Z) {
        }
    } else {
        try {
            var M = pt.sso;
            var L = M.InitPVA();
            if (L != false) {
                var I = M.GetPVACount();
                for (var W = 0; W < I; W++) {
                    var C = M.GetUin(W);
                    var D = M.GetAccountName(W);
                    var K = M.GetFaceIndex(W);
                    var U = M.GetNickname(W);
                    var P = M.GetGender(W);
                    var V = M.GetUinFlag(W);
                    var f = M.GetGTKey(W);
                    var R = M.GetST(W);
                    q_aUinList[W] = {uin: C, name: D, type: 0, face: K, nick: U, key: R}
                }
                if (typeof (M.GetKeyIndex) == "function") {
                    pt.keyindex = M.GetKeyIndex()
                }
            }
        } catch (Z) {
        }
    }
    switch (q_aUinList.length) {
        case 0:
            ptui_reportAttr(77430, 0.05);
            break;
        case 1:
            ptui_reportAttr(77431, 0.05);
            break;
        default:
            ptui_reportAttr(77432, 0.05)
    }
}
function hummer_getUinObj(B) {
    for (var A = 0; A < q_aUinList.length; A++) {
        var C = q_aUinList[A];
        if (C.uin == B) {
            return C
        }
    }
    return null
}
function unloadpage() {
    document.domain = g_domain;
    try {
        parent.document.body.onbeforeunload = function () {
        };
        parent.document.body.onunload = function () {
        };
        for (var A = 0; A < parent.parent.frames.length; A++) {
            parent.parent.frames[A].onunload = function () {
            };
            parent.parent.frames[A].onbeforeunload = function () {
            }
        }
        if (parent.parent != top) {
            for (var A = 0; A < parent.parent.parent.frames.length; A++) {
                parent.parent.parent.frames[A].onunload = function () {
                };
                parent.parent.parent.frames[A].onbeforeunload = function () {
                }
            }
        }
    } catch (B) {
    }
}
function hummer_login(H, F, C, D) {
    if (C == "") {
        C = "jump"
    }
    var A = (pt.ishttps ? "https://ssl.ptlogin2." : "http://ptlogin2.") + F + "/" + C + "?";
    var B = window.g_daid;
    var I = window.g_appid;
    var E = $.bom.query("regmaster");
    if (E == 2 && !pt.ishttps) {
        A = "http://ptlogin2.function.qq.com/jump?regmaster=2&"
    } else {
        if (E == 3 && !pt.ishttps) {
            A = "http://ptlogin2.crm2.qq.com/jump?regmaster=3&"
        }
    }
    A += "clientuin=" + H.uin + "&clientkey=" + H.key + "&keyindex=" + pt.keyindex + "&pt_aid=" + I + (B ? "&daid=" + B : "");
    if (pt.low_login == 1 && $("low_login_enable") && $("low_login_enable").checked) {
        A += "&low_login_enable=1&low_login_hour=" + $("low_login_hour").value
    }
    if (D != null && D != "") {
        var G = decodeURIComponent(D);
        if (G.indexOf("#") > -1) {
            G = G.replace(/#/g, "%23")
        }
        A += ("&" + G)
    }
    switch (parseInt(g_qtarget)) {
        case 0:
            unloadpage();
            parent.location.href = A;
            break;
        case 1:
            top.location.href = A;
            break;
        case 2:
            unloadpage();
            parent.parent.location.href = A;
            break;
        default:
            top.location.href = A
    }
}
function msg(A, B) {
    A = '<span style="color:#cc0000;">' + A + '</span><a href="http://support.qq.com/write.shtml?guest=1&fid=713&SSTAG=10011-' + B + '" target="_blank">' + g_strArray[6] + "</a>";
    try {
        var D = $("qlogin_loading");
        if ((D.style.display != "none") && ($("qlogin").style.display != "none")) {
            D.innerHTML = A;
            D.style.display = "";
            D.style.visibility = "visible"
        }
    } catch (C) {
    }
}
function browser_version() {
    var A = navigator.userAgent.toLowerCase();
    return A.match(/msie ([\d.]+)/) ? 2 : A.match(/firefox\/([\d.]+)/) ? 4 : A.match(/chrome\/([\d.]+)/) ? 6 : A.match(/opera.([\d.]+)/) ? 10 : A.match(/version\/([\d.]+).*safari/) ? 13 : 2
}
function xui_speedReport(E) {
    if (pt.isHttps || (window.flag2 && Math.random() > 0.5) || (!window.flag2 && Math.random() > 0.01)) {
        return
    }
    var B = "http://isdspeed.qq.com/cgi-bin/r.cgi?flag1=6000&flag2=1&flag3=" + browser_version();
    var C = 0;
    for (var D in E) {
        if (E[D] < 0 || E[D] > 300000) {
            continue
        }
        B += "&" + D + "=" + E[D];
        C++
    }
    if (C == 0) {
        return
    }
    var A = new Image();
    A.src = B
}
function xui_report() {
    if (Math.random() > 0.5) {
        return
    }
    if (!window.g_time) {
        return
    }
    if (g_time.time50 && g_time.time50 > 0 && g_time.time51 && g_time.time51 > 0 && g_time.time52 && g_time.time52 > 0 && g_time.time53 && g_time.time53 > 0) {
        var A = {};
        A["1"] = g_time.time51 - g_time.time50;
        A["6"] = g_time.time52 - g_time.time50;
        A["2"] = g_time.time54 - g_time.time50;
        A["3"] = g_time.time55 - g_time.time50;
        A["4"] = g_time.time54 - g_time.time53;
        A["5"] = g_time.time55 - g_time.time53
    }
    xui_speedReport(A)
}
function ptui_reportAttr(C, B) {
    if (Math.random() > (B || 1)) {
        return
    }
    url = location.protocol + "//ui.ptlogin2.qq.com/cgi-bin/report?id=" + C;
    var A = new Image();
    A.src = url
}
function pluginBegin() {
}
list();
try {
    $("loginbtn").focus()
} catch (e) {
}
;