public class CustomException extends Exception {
    private String error;
    CustomException(String error){
        this.error=error;
    }

    public String getError() {
        return error;
    }

}
