//import java.sql.*;
//import groovy.sql.*;
//import oracle.jdbc.driver.OracleTypes;
//
//Sql sql = Sql.newInstance('<%=url%>', 'oracle.jdbc.driver.OracleDriver');
//results = [:]
//<%
//isFunctionCall = ('function' == callType.toLowerCase())
//
//def generateReturnForFunction() {
//    if (isFunctionCall) {
//        def returnType = (outParameters.entrySet() as List).value[0][0];
//        generateOutParameter(returnType)
//        out.print('=')
//    }
//}
//
//def generateOutParameter(type) {
//    type = type.toUpperCase()
//    out.print('CURSOR' != type ? '${Sql.out(OracleTypes.' + type + ')}' : '${Sql.resultSet OracleTypes.' + type + '}')
//}
//
//def generateInParameter(name, type) {
//    type = type.toUpperCase()
//    out.print('${Sql.in(OracleTypes.' + type + ', ' + name + ')}')
//}
//
//def generateInParameters() {
//    inParameters.eachWithIndex { inParameter, i ->
//        generateInParameter(inParameter.key, inParameter.value[0])
//        if (i != inParameters.size() - 1)
//            out.print(',')
//    }
//}
//
//def generateOutParameters() {
//    if (outParameters.size() > (isFunctionCall ? 1 : 0))
//        out.print(',')
//
//    outParameters.eachWithIndex { outParameter, i ->
//        if ((isFunctionCall && i > 0) || !isFunctionCall) {
//            generateOutParameter(outParameter.value[0])
//            if (i != outParameters.size() - 1)
//                out.print(',')
//        }
//    }
//}
//
//def generateVariablesInClosure() {
//    outParameters.eachWithIndex { outParameter, i -> out.print(outParameter.key); if (i != outParameters.size() - 1) out.print(',') }
//}
//
//def generateAssignStatement(outParameter) {
//    out.println('\t' + 'results.' + outParameter.key + '=' + outParameter.key)
//}
//
//def generateAssignStatements() {
//    outParameters.eachWithIndex { outParameter, i ->
//        generateAssignStatement(outParameter)
//    }
//}
//%>
//sql.call(
//        """{<%generateReturnForFunction()%> call <%=callName%>(
//                            <%
//                                generateInParameters()
//                                generateOutParameters()
//                            %>
//                         )
//    }"""
//) {  <% generateVariablesInClosure() %> ->
//    <%
//    generateAssignStatements()
//    %>
//}
//results