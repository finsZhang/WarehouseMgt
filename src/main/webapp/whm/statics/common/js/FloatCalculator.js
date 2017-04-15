/**
 * Created by Walter on 2016/4/27.
 */
function FloatAdd(a,b){
    return (new BigDecimal(""+a).add(new BigDecimal(""+b)))+"";
}

function FloatSub(a,b){
    return (new BigDecimal(""+a).subtract(new BigDecimal(""+b)))+"";
}

function FloatMul(a,b){
    return (new BigDecimal(""+a).multiply(new BigDecimal(""+b)))+"";
}

function FloatDiv(a,b){//保留两位精度，四舍五入
    return (new BigDecimal(""+a).divide(new BigDecimal(""+b),2, MathContext.prototype.ROUND_HALF_UP))+"";
}