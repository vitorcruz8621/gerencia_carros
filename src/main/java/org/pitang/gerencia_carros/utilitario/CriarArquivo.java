package org.pitang.gerencia_carros.utilitario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CriarArquivo {
	
	private static ResourceLoader resourceLoader;
	
	public CriarArquivo(ResourceLoader resourceLoader) {
		CriarArquivo.resourceLoader = resourceLoader;
	}

	public static void createFile(MultipartFile filePostman) throws IOException {
		// Obtém o caminho do recurso (neste caso, o diretório onde você deseja colocar
		// o arquivo)
		Resource resource = resourceLoader.getResource("classpath:entidades/usuarios/");
		
		//System.out.println(resource.getFile().getAbsolutePath());

		// Obtém o caminho absoluto do diretório onde o arquivo deve ser criado
		File directory = resource.getFile().getParentFile();
		File file = new File(directory, "foto.png");

		try (OutputStream os = new FileOutputStream(file)) {
			// Cria um conteúdo para o arquivo
			String content = "Este é um exemplo de conteúdo para o arquivo foto.png";
			os.write(content.getBytes());
			System.out.println("Arquivo criado com sucesso em: " + file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
