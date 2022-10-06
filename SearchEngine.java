import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Search implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> searching = new ArrayList<>();

    public String handleRequest(URI url) {
        String returnSearch = " added!";
        System.out.println("Path: " + url.getPath());
        if (url.getPath().contains("/add")) { 
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) { 
                searching.add(parameters[1]);
                return parameters[1] + returnSearch;
            }
            return returnSearch;  
        
        } else if(url.getPath().contains("/search")) { 
            String[] parameters2 = url.getQuery().split("=");
            if (parameters2[0].equals("s")) { 
                for (String word : parameters2) { 
                    if (word.contains(parameters2[1])) { 
                        return word + returnSearch; 
                    }
                }
            }
            return returnSearch; 
        } else { 
            return "404 Not Found!"; 

        }
    } 
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Search());
    }
}
