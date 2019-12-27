#ifndef JGLWINDOW_H
#define JGLWINDOW_H

class GLWindow
{
	public:
		virtual void pre() = 0;
		virtual void init() = 0;
		virtual void loop() = 0;

		void show();
		int getWidth();
		int getHeight();

		void setSize(int width, int height);
		void setTitle(const char *title);
 
	private:
		int width, height;
		char *title;
};

#endif //JGLWINDOW_H