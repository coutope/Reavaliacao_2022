package br.edu.infnet.tp3aws.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.tp3aws.dtos.UsuarioDto;
import br.edu.infnet.tp3aws.models.Endereco;
import br.edu.infnet.tp3aws.models.Usuario;
import br.edu.infnet.tp3aws.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private AmazonClient amazonClient;
	
	@Autowired
	CepService cepService;
	
	@Autowired
	UsuarioRepository repository;
	
	public Usuario buscarUsuario(String email)
	{		
		return repository.findByEmail(email).get();
	}
	
	public void criarUsuario(UsuarioDto usuarioDto)
	{
		String urlFoto = amazonClient.uploadFile(usuarioDto.foto);
		Endereco enderecoRetornado = cepService.buscaEnderecoPor(usuarioDto.cep);
		String endereco = enderecoRetornado.logradouro + "," + enderecoRetornado.complemento + "," + enderecoRetornado.bairro + "," + enderecoRetornado.localidade;
		
		Usuario usuario = new Usuario();
		
		usuario.setNome(usuarioDto.nome);
		usuario.setEmail(usuarioDto.email);
		usuario.setCep(usuarioDto.cep);
		usuario.setTelefone(usuarioDto.telefone);
		usuario.setFoto(urlFoto);
		usuario.setEndereco(endereco);
		
		repository.save(usuario);
	}
	
	public void atualizarUsuario(UsuarioDto usuarioDto)
	{
		Usuario usuario = repository.findByEmail(usuarioDto.email).get();
		Endereco enderecoRetornado = cepService.buscaEnderecoPor(usuarioDto.cep);
		String endereco = enderecoRetornado.logradouro + "," + enderecoRetornado.bairro + "," + enderecoRetornado.localidade;
		
		amazonClient.deleteFileFromS3Bucket(usuario.getFoto());
		
		String urlFoto = amazonClient.uploadFile(usuarioDto.foto);
		
		usuario.setNome(usuarioDto.nome);
		usuario.setEmail(usuarioDto.email);
		usuario.setCep(usuarioDto.cep);
		usuario.setTelefone(usuarioDto.telefone);
		usuario.setFoto(urlFoto);
		usuario.setEndereco(endereco);
		
		repository.save(usuario);
	}
	
}
