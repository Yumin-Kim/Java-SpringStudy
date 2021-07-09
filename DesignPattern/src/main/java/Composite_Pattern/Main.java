package Composite_Pattern;

/*
 	컨테이너와 내용물을 같게 다루기
 	
 */

public class Main {

	public static void main(String[] args) {
		Folder 
		root = new Folder("root")
		,home= new Folder("home")
		,grama= new Folder("grama")
		,music= new Folder("musice")
		, picture= new Folder("picture")
		, doc= new Folder("doc")
		,user= new Folder("user");
		FileSystem 
		track1 = new FileSystem("track1")
		,track2= new FileSystem("track2")
		,pic1 = new FileSystem("pic1")
		,doc1 = new FileSystem("doc1")
		,java = new FileSystem("java");
		
		
		root.addComponent(home);
		home.addComponent(grama);
			grama.addComponent(music);
				music.addComponent(track1);
				music.addComponent(track2);
			grama.addComponent(picture);
				picture.addComponent(pic1);
			grama.addComponent(doc);
				doc.addComponent(doc1);
				
			
		root.addComponent(user);
			user.addComponent(java);
	}
	
	private static void show(Component compenent) {
		System.out.println(compenent.getClass().getName() + "|" + compenent.getName() );
		if(compenent instanceof Folder) {
			for(Component c : ((Folder)compenent).getChildren())
		}
		
	}
	

}
