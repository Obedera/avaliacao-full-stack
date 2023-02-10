package com.obedera.transferencias.financeiras.http;

public final class URLMapping {

    private URLMapping() {
    }

    public static final String ROOT_API_PATH = "/api";

    public static final String ROOT_API_WS_TRANSFERENCIA = ROOT_API_PATH + "/v1/transferencia";
    public static final String SAVE_TRANSFERENCIA_PATH = "/confirmar";
    public static final String RESUME_TRANSFERENCIA_PATH = "/resumo";
    public static final String LIST_TRANSFERENCIA_PATH = "/historico";

}
