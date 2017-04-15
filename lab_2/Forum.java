import java.util.*;

public class Forum {
    private ArrayList<Thread> listOfThreads = new ArrayList<Thread>();
    private ArrayList<User> listOfUsers = new ArrayList<User>();


    public void run() {
        //define your main method here
        Scanner sc = new Scanner(System.in);

        Forum myForum = new Forum();

        int numUsers = sc.nextInt();
        sc.nextLine();//consume trailing line
        for (int i=0; i<numUsers; i++){
            listOfUsers.add(new User(sc.nextLine()));
        }

        int numThreads =sc.nextInt();
        sc.nextLine();//consume trailing line
        for (int i=0; i<numThreads;i++){
            listOfThreads.add(new Thread(sc.nextLine()));
        }

        int numQueries =sc.nextInt();
        sc.nextLine();//consume trailing line

        //runs the query
        for(int i=0; i<numQueries; i++){

            switch(sc.next()){
                case "post":
                    post(sc);
                    break;
                case "count":
                    count(sc);
                    break;
                case "numpost":
                    numpost(sc);
                    break;
                case "maxpost":
                    maxpost(sc);
                    break;
                case "content":
                    content(sc);
                    break;
                default:
                    break;
            }
        }


    }

    public static void main(String[] args){
        Forum myForum = new Forum();
        myForum.run();
    }

    ////////////////////////////////////////////////////////////////////////////

    //helpers
    public  Thread getThread(String name){
        for(Thread thread: listOfThreads){
            if (thread.getName().equals(name)){
                return thread;
            }
        }
        return null;
    }

    public User getUser(String name){
        for(User user: listOfUsers){
            if (user.getUserName().equals(name)){
                return user;
            }
        }
        return null;
    }
    ////////////////////////////////////////////////////////////////////////////////

    //queries
    public void post(Scanner sc){
        String threadName = sc.next().trim();
        String userName = sc.next().trim();
        String message = sc.nextLine().trim();

        Thread thread = getThread(threadName);
        User user = getUser(userName);
        if(thread==null){
            System.out.println("no such thread");
            return;
        }

        if (user==null){
            System.out.println("no such user");
            return;
        }

        //creates new post
        thread.addPost( user, message);
        user.setNumPosts();//increments 1
        System.out.println(message);
    }

    public void count(Scanner sc){
        int numThread= sc.nextInt();

        int postCount=0;
        while (numThread-- >0){
            String threadName= sc.next();

            Thread thread= getThread(threadName);

            postCount += thread.getNumPosts();
        }
        System.out.println(postCount);
    }

    public void numpost(Scanner sc){
        String userName = sc.nextLine().trim();
        User user= getUser(userName);

        if(user==null)
            System.out.println("no such user");
        else{
            System.out.println(user.getNumPosts());
        }
    }

    public void maxpost(Scanner sc){
        String threadName = sc.nextLine().trim();

        Thread thread = getThread(threadName);
        if (thread==null){
            System.out.println("no such thread");
            return;
        }

        //arraylist of users in parallel
        ArrayList<String> userList = new ArrayList<String>();
        ArrayList<Integer> countList=  new ArrayList<Integer>();

        //populate lists
        for(User user: listOfUsers){
            userList.add(user.getUserName());//populate usernames
            countList.add(0);//set all to 0
        }
        //incrementing countlist
        for(Post post: thread.getListOfPosts()){
            for(String username: userList){
                if (post.getUserName().equals(username) ){
                    Integer newValue =  countList.get(userList.indexOf(username)) +1; 
                    countList.set(userList.indexOf(username),newValue);
                    break;
                }
            }
        }

        ArrayList<String> userNamesWithMostPosts= new ArrayList<String>();
        int top=0; 
        //find biggest number
        for(Integer integer: countList){
            if (integer>top){
                top=integer;
            }
        }

        //add to list
        for(Integer integer: countList){
            if (integer==top){
                int index= countList.indexOf(integer);//index ofreturns first occurence only
                userNamesWithMostPosts.add(userList.get(index));
                countList.set(index, 0);//so that it will not intefere with searching
            }
        }
        //sort
        Collections.sort(userNamesWithMostPosts);
        System.out.println(userNamesWithMostPosts.get(0));

    }

    public void content(Scanner sc){
        String threadName = sc.next().trim();
        int postNumber = sc.nextInt();
        Thread thread = getThread(threadName);
        if (thread==null){
            System.out.println("no such thread");
            return;
        }
        Post post = thread.getPost(postNumber);
        if (post==null){
            System.out.println("no such post");
            return;
        }
        System.out.println(post.getMessage());
    }


}

class Thread {
    //define the appropriate attributes, constructor, and methods here
    private ArrayList<Post> listOfPosts= new ArrayList<Post>();
    private String topicName;
    private int numPosts=0;

    public Thread(String name){
        topicName = name;
    }

    public String getName(){
        return topicName;
    }

    public int getNumPosts(){
        return numPosts;
    }

    public void setNumPosts(){
        numPosts++;
    }

    public ArrayList<Post> getListOfPosts(){
        return listOfPosts;
    }

    public void addPost(User author, String message){
        numPosts++;
        listOfPosts.add(new Post(numPosts, author.getUserName(), message));
    }

    public Post getPost(int postNo){
        //check for valid post no
        if (postNo <= listOfPosts.size()){
            return listOfPosts.get(postNo-1);
        }else{
            return null;
        }
    }
}

class Post {
    //define the appropriate attributes, constructor, and methods here
    private int postNumber;
    private String userName;
    private String message;

    public Post(int postNumber, String name, String message){
        this.postNumber= postNumber;
        userName= name;
        this.message= message;
    }

    public int getPostNumber(){
        return postNumber;
    }
    public void setPostNumber(int num){
        postNumber= num;
    }

    public String getUserName(){
        return userName;
    }

    public String getMessage(){
        return message;
    }


}

class User {
    //define the appropriate attributes, constructor, and methods here
    private String userName;
    private int numPosts=0;

    public User(String name){
        userName = name;
    }

    public String getUserName(){
        return userName;
    }

    public int getNumPosts(){
        return numPosts;
    }

    public void setNumPosts(){
        numPosts++;
    }
}
