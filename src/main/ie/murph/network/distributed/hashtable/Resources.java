package main.ie.murph.network.distributed.hashtable;

public enum Resources {
	
	CHORD_PEER_TO_PEER("https://en.m.wikipedia.org/wiki/Chord_(peer-to-peer)"),
	CHORD_PEER_TO_PEER_IMAGE("https://www.google.com/search?q=Chord+(peer-to-peer)&client=firefox-b-d&channel=crow&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiXmZn0g6DjAhXhUBUIHesvCpMQ_AUIECgB&biw=1920&bih=966"),
	COLLEGE_LECTURE_2016("https://amplab.github.io/cs262a-fall2016/notes/19-Chord-Dynamo.pdf"),
	GAME("https://pzemtsov.github.io/2015/04/24/game-of-life-hash-tables-and-hash-codes.html"),
	HASHTABLE("https://www.baeldung.com/java-hash-table");
	
	private final String text;

    private Resources(final String text) 
    {
        this.text = text;
    }

    @Override
    public String toString() 
    {
        return this.text;
}


}
