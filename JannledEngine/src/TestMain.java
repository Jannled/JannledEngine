import java.io.File;

import com.github.jannled.engine.model.loader.ModelLoader;

public class TestMain
{
	ModelLoader loader = new ModelLoader(null);
	
	public TestMain(File file)
	{
		loader.load(file);
	}
	
	public static void main(String[] args)
	{
		new TestMain(new File("src/Suzanna.obj"));
	}
}
