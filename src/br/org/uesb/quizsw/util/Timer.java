package br.org.uesb.quizsw.util;

import br.org.uesb.quizsw.view.Janela_Quiz;

public class Timer extends Thread {

	private long time;
	Janela_Quiz parent;
	
	public Timer(long time, Janela_Quiz parent) {
		this.time = time;
		this.parent = parent;
	}
	
	@Override
	public void run() {
		try {
			this.sleep(time*1000*60);
			parent.endQuiz("Tempo Esgotado!");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
